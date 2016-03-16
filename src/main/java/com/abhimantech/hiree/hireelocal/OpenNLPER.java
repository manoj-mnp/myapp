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

	public OpenNLPER(String document, SentenceDetector sd, NameFinderME mf,
			TokenizerME wordBreaker) {
		System.out.println("got doc");
		this.sd = sd;
		this.myNameFinder = mf;
		this.wordBreaker = wordBreaker;
		doc = document;
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
				for(String find : TokenHelper.SUMMARY_TOKENS){
					int index = text.indexOf(find);
					if(index>0){
						System.out.println("Found keyword:::: "+find);
					}
				}
				Set<String> emails = new HashSet<String>();
				String emailsStr = "";
				while (matcher.find()) {
					emails.add(matcher.group());
					emailsStr = emailsStr+matcher.group()+",";
				}
				count++;
				 String sql = "INSERT INTO "+SQLiteJDBC.RESUME_TABLENAME+" (FileName,FilePath,Email,PhoneNum,resumeTxt,isProcessed) " +
		                   "VALUES ('"+file.getName()+"', '"+file.getAbsolutePath()+"', '"+emailsStr+"', '"+phoneNum+"', '', 1 );";  
				SQLiteJDBC.inserData(sql);
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
	
	
	public static void toHtml() throws IOException, TransformerConfigurationException, SAXException, TikaException
	{
	    byte[] file = Files.toByteArray(new File("/Users/abhimantechnologies/Documents/Parser/sillycat-resume-parse/Manoj_Resume_25_mar.pdf"));
	    AutoDetectParser tikaParser = new AutoDetectParser();
	    HWPFDocument doc = new HWPFDocument(new FileInputStream(new File("/Users/abhimantechnologies/Documents/Parser/resumes/1421588452637_90578.doc")));
	    Range r = doc.getRange();
        ArrayList titles = new ArrayList();
        for(int i = 0; i<r.numCharacterRuns(); i++)
        {
            CharacterRun cr = r.getCharacterRun(i);
            if(cr.isBold()){
            	System.out.println("bold:::"+ cr.text());
            }
        }

//        try {
//            for (int i = 0; i < we.getText().length() - 1; i++) {
//                int startIndex = i;
//                int endIndex = i + 1;
//                Range range = new Range(startIndex, endIndex, doc);
//                CharacterRun cr = range.getCharacterRun(0);
//
//                if (cr.isBold() || cr.isItalic() || cr.getUnderlineCode() != 0) {
//                    while (cr.isBold() || cr.isItalic() || cr.getUnderlineCode() != 0) {
//                        i++;
//                        endIndex += 1;
//                        range = new Range(endIndex, endIndex + 1, doc);
//                        cr = range.getCharacterRun(0);
//                    }
//                    range = new Range(startIndex, endIndex - 1, doc);
//                    titles.add(range.text());
//                }
//
//            }
//        }
//        catch (IndexOutOfBoundsException iobe) {
//            //sometimes this happens have to find out why.
//        }

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
	    TransformerHandler handler = factory.newTransformerHandler();
	    handler.getTransformer().setOutputProperty(OutputKeys.METHOD, "xml");
	    handler.getTransformer().setOutputProperty(OutputKeys.INDENT, "yes");
	    handler.getTransformer().setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    handler.setResult(new StreamResult(out));
	    ExpandedTitleContentHandler handler1 = new ExpandedTitleContentHandler(handler);

	    tikaParser.parse(new ByteArrayInputStream(file), handler1, new Metadata());
	   // System.out.println(new String(out.toByteArray(), "UTF-8"));
	}


//	private static List<String> getMyDocsFromSomewhere() {
//		List<String> list = new ArrayList<String>();
//		// this should return an object that has all the info about the doc you
//		// want
//		Tika tika = new Tika();
//		String text = null;
//		Parser parser = new AutoDetectParser();
//		BodyContentHandler handler = new BodyContentHandler();
//		ParseContext context = new ParseContext();
//		Metadata metadata = new Metadata();
//
//		// fetch the content
//		try {
//			text = tika.parseToString(new File(file));
//			list.add(text);
//			String input = "manada@fgafa.com";
//			// int index =text.indexOf("@");
//			// System.out.println(index);
//			// String regex =
//			// ".*(\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\b).*";
//			// Pattern p = Pattern.compile(EMAIL_PATTERN);
//			// Matcher m = p.matcher(input);
//			// System.out.println(m.matches()+ " text ");
//			Pattern p = Pattern.compile(
//					"\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
//					Pattern.CASE_INSENSITIVE);
//			Matcher matcher = p.matcher(text);
//			Set<String> emails = new HashSet<String>();
//			while (matcher.find()) {
//				emails.add(matcher.group());
//			}
//			System.out.println(emails.toString());
//			// if (m.matches()) {
//			// String email = m.group(0);
//			// System.out.println(email);
//			// }
//			EmailScanner scanner = new EmailScanner(false);
//			LinkExtractor linkExtractor = LinkExtractor.builder().build();
//			Iterable<LinkSpan> links = linkExtractor.extractLinks(input);
//			LinkSpan link = links.iterator().next();
//			link.getType(); // LinkType.URL
//			link.getBeginIndex(); // 17
//			link.getEndIndex(); // 32
//			input.substring(link.getBeginIndex(), link.getEndIndex()); // "http://test.com"
//
//		} catch (TikaException e) {
//			e.printStackTrace();
//		}catch (IOException e){
//			e.printStackTrace();
//		}
//		return list;
//	}
		
	public void run() {
		try {
			processFiles(_fileList);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}
	}

	public void process(String document) throws Exception {

		// System.out.println(document);
		// user instantiates the non static entitylinkerproperty object and
		// constructs is with a pointer to the prop file they need to use
		String modelPath = "C:\\apache\\entitylinker\\";

		// input document
		myNameFinder.clearAdaptiveData();
		// user splits doc to sentences
		String[] sentences = sd.sentDetect(document);
		// get the sentence spans
		Span[] sentenceSpans = sd.sentPosDetect(document);
		Span[][] allnamesInDoc = new Span[sentenceSpans.length][];
		String[][] allTokensInDoc = new String[sentenceSpans.length][];

		for (int sentenceIndex = 0; sentenceIndex < sentences.length; sentenceIndex++) {
			String[] stringTokens = wordBreaker
					.tokenize(sentences[sentenceIndex]);
			Span[] tokenSpans = wordBreaker
					.tokenizePos(sentences[sentenceIndex]);
			Span[] spans = myNameFinder.find(stringTokens);
			allnamesInDoc[sentenceIndex] = spans;
			allTokensInDoc[sentenceIndex] = stringTokens;
		}

		// now access the data like this...
		for (int s = 0; s < sentenceSpans.length; s++) {
			Span[] namesInSentence = allnamesInDoc[s];
			String[] tokensInSentence = allTokensInDoc[s];
			String[] entities = Span.spansToStrings(namesInSentence,
					tokensInSentence);
			for (String entity : entities) {
				// start building up the XML here....
				System.out.println(entity + " Was in setnence " + s + " @ ");
			}
		}

	}
}