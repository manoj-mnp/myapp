package com.abhimantech.hiree.hireelocal;

import java.io.IOException;
import java.io.InputStream;

import com.abhimantech.hiree.hireelocal.utils.TokenHelper;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class OpenNLPSentenceMain {

	public static void processSentence(String str) {
		InputStream modelIn = OpenNLPSentenceMain.class.getClassLoader()
				.getResourceAsStream("models/en-sent.bin");

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
			value = value.replaceAll(" ", "").replace("\t", "").replace("\n", "");
			//System.out.println(value);
			for (String find : TokenHelper.SUMMARY_TOKENS) {
				if (value.indexOf(find) >= 0) {
					int findLegth = find.length();
					int sentenceLength = value.length();
					float percentage = (findLegth*100)/sentenceLength;
					System.out.println("found possible token ::: "+find+" "+percentage+" "+findLegth+" "+sentenceLength);
					if(percentage>75){
					}
				}
			}
		}
	}

}
