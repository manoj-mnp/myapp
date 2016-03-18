package com.abhimantech.hiree.hireelocal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ExpandedTitleContentHandler;
import org.xml.sax.SAXException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.abhimantech.hiree.hireelocal.callbacks.FileProcessingCallback;
import com.abhimantech.hiree.hireelocal.utils.TokenHelper;
import com.google.common.io.Files;
import com.google.i18n.phonenumbers.PhoneNumberMatch;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

public class OpenNLPER implements Runnable {

	static TokenizerModel tm = null;
	static TokenNameFinderModel locModel = null;
	String doc;
	NameFinderME myNameFinder;
	TokenizerME wordBreaker;
	SentenceDetector sd;
	static Tika tika = new Tika();
	FileProcessingCallback _callback;
	Collection<File> _fileList;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public OpenNLPER(FileProcessingCallback callback,Collection<File> fileList) {
		this._callback = callback;
		this._fileList = fileList;
	}

	
	public void processFiles(Collection<File> fileList){
		String text = null;
		int count = 0;
		int total = fileList.size();
		for (Iterator<File> iterator = fileList.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			System.out.println(file);
			try {
				text = tika.parseToString(file);
				Pattern p = Pattern.compile(
						"\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
						Pattern.CASE_INSENSITIVE);
				ArrayList<String> phones = extractPhoneNumber(text);
				String phoneNum = "";
				for (String ph : phones) {
					phoneNum = phoneNum+phones+",";
				}
				Matcher matcher = p.matcher(text);
				Set<String> emails = new HashSet<String>();
				String emailsStr = "";
				while (matcher.find()) {
					emails.add(matcher.group());
					emailsStr = emailsStr+matcher.group()+",";
				}
				DocumentObject doc = new DocumentObject(count, emailsStr, phoneNum, text);
				DocumentAddRequest request = new DocumentAddRequest(doc, 1.0, true, 1000);
				SolrDocumentRequest docReq = new SolrDocumentRequest(request);
				HireeRetrofit.getApi().solrUpdateDocument(docReq, new Callback<Response>() {
					
					public void success(Response arg0, Response arg1) {
						// TODO Auto-generated method stub
					
					}
					
					public void failure(RetrofitError arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				//OpenNLPSentenceMain.processSentence(text);
				count++;
				 String sql = "INSERT INTO "+SQLiteJDBC.RESUME_TABLENAME+" (FileName,FilePath,Email,PhoneNum,resumeTxt,isProcessed) " +
		                   "VALUES ('"+file.getName()+"', '"+file.getAbsolutePath()+"', '"+emailsStr+"', '"+phoneNum+"', '', 1 );";  
				//SQLiteJDBC.inserData(sql);
				_callback.updateProgress(count, total);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TikaException e){
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<String> extractPhoneNumber(String input) {
		ArrayList<String> phoneNumber = new ArrayList<String>();
		java.util.Iterator<PhoneNumberMatch> existsPhone = PhoneNumberUtil
				.getInstance().findNumbers(input, "IN").iterator();

		while (existsPhone.hasNext()) {
			PhoneNumberMatch phone = existsPhone.next();
			String phNo = phone.number().getCountryCode() + "-"
					+ phone.number().getNationalNumber();
			if (!phoneNumber.contains(phNo)) {
				phoneNumber.add(phone.number().getCountryCode() + "-"
						+ phone.number().getNationalNumber());
			}
		}
		return phoneNumber;
	}

	public void run() {
		try {
			processFiles(_fileList);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}
	}
}