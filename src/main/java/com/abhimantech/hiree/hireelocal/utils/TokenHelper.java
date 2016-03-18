package com.abhimantech.hiree.hireelocal.utils;

public class TokenHelper {
	public static String[] EDUCATION_TOKENS = { "ACADEMIC BACKGROUND",
			"Academic Background", "ACADEMIC EXPERIENCE",
			"Academic Experience", "ACADEMIC TRAINING", "Academic Training",
			"APPRENTICESHIPS", "Apprenticeships", "CERTIFICATION",
			"Certification", "CERTIFICATIONS", "Certifications",
			"COLLEGE ACTIVITIES", "College Activities",
			"COURSE PROJECT EXPERIENCE", "Course Project Experience",
			"COURSES", "Courses", "EDUCATION", "Education",
			"EDUCATION AND TRAINING", "Education and Training",
			"EDUCATIONAL BACKGROUND", "Educational Background",
			"EDUCATIONAL QUALIFICATIONS", "Educational Qualifications",
			"EDUCATIONAL TRAINING", "Educational Training",
			"INTERNSHIP EXPERIENCE", "Internship Experience", "INTERNSHIPS",
			"Internships", "Patent", "PATENT", "Patents", "PATENTS",
			"Professional Training", "PROFESSIONAL TRAINING", "Programs",
			"PROGRAMS", "RELATED COURSE PROJECTS", "Related Course Projects",
			"RELATED COURSES", "Related Courses", "Special Training",
			"SPECIAL TRAINING", "Study", "STUDY", "Training", "TRAINING",
			"Workshop", "WORKSHOP", "Workshops", "WORKSHOPS" };

	public static String[] SUMMARY_TOKENS = { "ABOUT ME", "About Me", "BRIEF OVERVIEW", "Brief Overview", 
			"CAREER SNAPSHOT", "Career Snapshot", "CAREER GOAL", "Career Goal",
			"CAREER OBJECTIVE", "Career Objective", "CAREER PROFILE", "Career Profile", "CAREER SUMMARY",
			"Career Summary", "EMPLOYMENT OBJECTIVE", "Employment Objective", "EXECUTIVE SUMMARY", "Executive Summary",
			"HIGHLIGHTS", "Highlights", "JOB OBJECTIVE", "Job Objective",
			"OBJECTIVE", "Objective", "PERSONAL PROFILE", "Personal profile","PROFESSIONAL OBJECTIVE",
			"Professional Objective", "Professional Profile",
			"PROFESSIONAL PROFILE", "Professional Summary",
			"PROFESSIONAL SUMMARY", "PROFILE SUMMARY", "Profile Summary", "PROFILE", "Profile", "RESUME SUMMARY", "Resume Summary", "SUMMARY", "Summary",
			"SUMMARY OF QUALIFICATIONS", "Summary of Qualifications",
			"SUMMARY STATEMENT", "Summary Statement", "SYNOPSIS", "synopsis", "TECHNICAL SUMMARY",
			"Technical Summary" };
	
	public static String[] ACCOMPLISHMENT_TOKENS= {
			"Accomplishments",
			"Key Accomplishments",
			"Conference Presentations",
			"CONFERENCE PRESENTATIONS",
			"Conventions",
			"CONVENTIONS",
			"Current Research Interests",
			"CURRENT RESEARCH INTERESTS",
			"Dissertation",
			"DISSERTATION",
			"Dissertations",
			"DISSERTATIONS",
			"Exhibits",
			"EXHIBITS",
			"Licenses",
			"LICENSES",
			"Papers",
			"PAPERS",
			"Presentations",
			"PRESENTATIONS",
			"Professional Publications",
			"PROFESSIONAL PUBLICATIONS",
			"Publications",
			"PUBLICATIONS",
			"Research",
			"RESEARCH",
			"Research Grants",
			"RESEARCH GRANTS",
			"Research Interests",
			"RESEARCH INTERESTS",
			"Research Projects",
			"RESEARCH PROJECTS",
			"Thesis / Theses",
			"THESIS / THESES"
	};
	
	public static String[] AWARDS_TOKENS = {
			"Academic Honors",
			"ACADEMIC HONORS",
			"Accolades",
			"ACCOLADES",
			"Accomplishments",
			"ACCOMPLISHMENTS",
			"Achievements",
			"ACHIEVEMENTS",
			"Activities and Honors",
			"ACTIVITIES AND HONORS",
			"Awards",
			"AWARDS",
			"Distinctions",
			"DISTINCTIONS",
			"Endorsements",
			"ENDORSEMENTS",
			"Fellowship",
			"FELLOWSHIP",
			"Fellowships",
			"FELLOWSHIPS",
			"Honors",
			"HONORS",
			"Scholarship",
			"SCHOLARSHIP",
			"Scholarships",
			"SCHOLARSHIPS"
	};
	
	public static String[] CREDIBILITY_TOKENS = {
			"Portfolio",
			"PORTFOLIO",
			"Recommendations",
			"RECOMMENDATIONS",
			"References",
			"REFERENCES",
			"Social Media Profiles",
			"SOCIAL MEDIA PROFILES",
			"Social Profiles",
			"SOCIAL PROFILES",
			"Testimonials",
			"TESTIMONIALS",
			"Web Portfolio",
			"WEB PORTFOLIO",
			"Websites",
			"WEBSITES"
	};
	
	public static String[] EXTRACURRICULAR_TOKENS = {
			"Activities",
			"ACTIVITIES",
			"Activities and Honors",
			"ACTIVITIES AND HONORS",
			"Affiliations",
			"AFFILIATIONS",
			"Associations",
			"ASSOCIATIONS",
			"Athletic Involvement",
			"ATHLETIC INVOLVEMENT",
			"Civic Activities",
			"CIVIC ACTIVITIES",
			"Community Involvement",
			"COMMUNITY INVOLVEMENT",
			"Extra-Curricular Activities",
			"EXTRA-CURRICULAR ACTIVITIES",
			"Honors",
			"HONORS",
			"Memberships",
			"MEMBERSHIPS",
			"Professional Activities",
			"PROFESSIONAL ACTIVITIES",
			"Professional Affiliations",
			"PROFESSIONAL AFFILIATIONS",
			"Professional Associations",
			"PROFESSIONAL ASSOCIATIONS",
			"Professional Memberships",
			"PROFESSIONAL MEMBERSHIPS",
			"Volunteer Experience",
			"VOLUNTEER EXPERIENCE",
			"Volunteer Work",
			"VOLUNTEER WORK"
	};
	
	public static String[] WORKEXPERIENCE_TOKENS = {
			"ADDITIONAL EXPERIENCE",
			"Additional Experience",
			"ARMY EXPERIENCE",
			"Army Experience",
			"CAREER RELATED EXPERIENCE",
			"Career Related Experience",
			"EMPLOYMENT HISTORY",
			"Employment History",
			"EMPLOYMENT EXPERIENCE",
			"Employment Experience",
			"EXPERIENCE",
			"Experience",
			"EXPERIENCE SUMMARY",
			"Experience Summary",
			"FREELANCE",
			"Freelance",
			"FREELANCE EXPERIENCE",
			"Freelance Experience",
			"MILITARY BACKGROUND",
			"Military Background",
			"MILITARY EXPERIENCE",
			"Military Experience",
			"ORGANISATIONAL EXPERIENCE",
			"Organisational Experience",
			"PROFESSIONAL BACKGROUND",
			"Professional Background",
			"PROFESSIONAL EXPERIENCE",
			"Professional Experience",
			"Project",
			"PROJECT",
			"Projects",
			"PROJECTS",
			"Related Experience",
			"RELATED EXPERIENCE",
			"WORK EXPERIENCE",
			"Work Experience",
			"Work History",
			"WORK HISTORY"
	};
	
	public static String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}

	
}
