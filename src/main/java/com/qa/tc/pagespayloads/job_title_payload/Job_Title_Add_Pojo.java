package com.qa.tc.pagespayloads.job_title_payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class Job_Title_Add_Pojo {
    private String jobTitleName;
    private String jobDescription;
    private String note;
    private String currentJobSpecification;
    private JobSpecification jobSpecification;

    @Override
    public String toString() {
        return "Request_Job_Title_Add_Pojo [jobTitleName=" + jobTitleName + ", jobDescription=" + jobDescription
                + ", note=" + note + ", currentJobSpecification=" + currentJobSpecification + ", jobSpecification="
                + jobSpecification + "]";
    }

    @Builder
    @Setter
    @Getter
    public static class JobSpecification {
        private String base64;
        private String filename;
        private String filesize;
        @Override
		public String toString() {
			return "JobSpecification [base64=" + base64 + ", filename=" + filename + ", filesize=" + filesize
					+ ", filetype=" + filetype + "]";
		}
		private String filetype;
    }
}
