package com.abhimantech.hiree.hireelocal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.abhimantech.hiree.hireelocal.utils.ResumeData;
import com.abhimantech.hiree.hireelocal.utils.TokenHelper;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class OpenNLPSentenceMain {
	
	public static void processSentence(String str) {
		ArrayList<ResumeData> resumeKeyWordsDataList = new ArrayList<ResumeData>();
		InputStream modelIn = OpenNLPSentenceMain.class.getClassLoader()
				.getResourceAsStream("models/en-sent.bin");
		String resumeString = str;
		SentenceModel model = null;
		try {
			model = new SentenceModel(modelIn);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
		Span[] spans = sentenceDetector.sentPosDetect(str);
		double[] sentenceProbabilities = sentenceDetector
				.getSentenceProbabilities();

		for (int i = 0; i < spans.length; i++) {
			int start = spans[i].getStart();
			int end = spans[i].getEnd();
			String value = str.substring(start, end);
			//value = value.replaceAll(" ", "").replace("\t", "").replace("\n", "").replace("\r", "");
			//System.out.println("--" + value +"-->");
			//value = value.replaceAll("[^a-zA-Z]", "");
			
			//System.out.println(value.split("\n") +"-->");
			//System.out.println(value);
//----->		for (String find : TokenHelper.SUMMARY_TOKENS) {
//				String[] arr = value.split("\n");
//				for(String st : arr){
//					//System.out.println("<--------------"+st+"------------>");
//					if (st.indexOf(find) >= 0) {
//						int findLegth = find.length();
//						int sentenceLength = st.length();
//						float percentage = (findLegth*100)/sentenceLength;
//						if(percentage>75){
//							System.out.println("found possible token ::: "+find+" "+percentage+" "+findLegth+" "+sentenceLength);
//						}
//					}
//				}
//				
//----->			}
			
			String[] arr = value.split("\n");
			for (String find : TokenHelper.ACCOMPLISHMENT_TOKENS) {
				for(String st : arr){
					//System.out.println("<--------------"+st+"------------>");
					if (st.indexOf(find) >= 0) {
						int findLegth = find.length();
						int sentenceLength = st.length();
						float percentage = (findLegth*100)/sentenceLength;
						if(percentage>75){
							resumeKeyWordsDataList.add(new ResumeData(find, "ACCOMPLISHMENT", resumeString.indexOf(find)));
							//System.out.println("found possible token ::: "+find+" "+percentage+" "+findLegth+" "+sentenceLength);
						}
					}
				}
				
			}
			for (String find : TokenHelper.AWARDS_TOKENS) {
				for(String st : arr){
					//System.out.println("<--------------"+st+"------------>");
					if (st.indexOf(find) >= 0) {
						int findLegth = find.length();
						int sentenceLength = st.length();
						float percentage = (findLegth*100)/sentenceLength;
						if(percentage>75){
							resumeKeyWordsDataList.add(new ResumeData(find, "AWARDS", resumeString.indexOf(find)));
							//System.out.println("found possible token ::: "+find+" "+percentage+" "+findLegth+" "+sentenceLength);
						}
					}
				}
				
			}
			for (String find : TokenHelper.CREDIBILITY_TOKENS) {
				for(String st : arr){
					//System.out.println("<--------------"+st+"------------>");
					if (st.indexOf(find) >= 0) {
						int findLegth = find.length();
						int sentenceLength = st.length();
						float percentage = (findLegth*100)/sentenceLength;
						if(percentage>75){
							resumeKeyWordsDataList.add(new ResumeData(find, "CREDIBILITY", resumeString.indexOf(find)));
							//System.out.println("found possible token ::: "+find+" "+percentage+" "+findLegth+" "+sentenceLength);
						}
					}
				}
				
			}
			for (String find : TokenHelper.EDUCATION_TOKENS) {
				for(String st : arr){
					//System.out.println("<--------------"+st+"------------>");
					if (st.indexOf(find) >= 0) {
						int findLegth = find.length();
						int sentenceLength = st.length();
						float percentage = (findLegth*100)/sentenceLength;
						if(percentage>75){
							resumeKeyWordsDataList.add(new ResumeData(find, "EDUCATION", resumeString.indexOf(find)));
							//System.out.println("found possible token ::: "+find+" "+percentage+" "+findLegth+" "+sentenceLength);
						}
					}
				}
				
			}
			for (String find : TokenHelper.EXTRACURRICULAR_TOKENS) {
				for(String st : arr){
					//System.out.println("<--------------"+st+"------------>");
					if (st.indexOf(find) >= 0) {
						int findLegth = find.length();
						int sentenceLength = st.length();
						float percentage = (findLegth*100)/sentenceLength;
						if(percentage>75){
							resumeKeyWordsDataList.add(new ResumeData(find, "EXTRACURRICULAR", resumeString.indexOf(find)));
							//System.out.println("found possible token ::: "+find+" "+percentage+" "+findLegth+" "+sentenceLength);
						}
					}
				}
				
			}
			
			for (String find : TokenHelper.SUMMARY_TOKENS) {
				for(String st : arr){
					//System.out.println("<--------------"+st+"------------>");
					if (st.indexOf(find) >= 0) {
						int findLegth = find.length();
						int sentenceLength = st.length();
						float percentage = (findLegth*100)/sentenceLength;
						if(percentage>75){
							resumeKeyWordsDataList.add(new ResumeData(find, "SUMMARY", resumeString.indexOf(find)));
							//System.out.println("found possible token ::: "+find+" "+percentage+" "+findLegth+" "+sentenceLength);
						}
					}
				}
				
			}
			
			for (String find : TokenHelper.WORKEXPERIENCE_TOKENS) {
				for(String st : arr){
					//System.out.println("<--------------"+st+"------------>");
					if (st.indexOf(find) >= 0) {
						int findLegth = find.length();
						int sentenceLength = st.length();
						float percentage = (findLegth*100)/sentenceLength;
						if(percentage>75){
							resumeKeyWordsDataList.add(new ResumeData(find, "WORKEXPERIENCE", resumeString.indexOf(find)));
							//System.out.println("found possible token ::: "+find+" "+percentage+" "+findLegth+" "+sentenceLength);
						}
					}
				}
				
			}
			
		}
		for(ResumeData data : resumeKeyWordsDataList){
			System.out.println(data.printString());
		}
		System.out.println("\n\n");
		
	}	
}
