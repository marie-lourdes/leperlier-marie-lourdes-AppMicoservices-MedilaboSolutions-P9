package com.mmedicalreport.microservicemedicalReports;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mmedicalreport.microservicemedicalReports.model.MedicalReport;
import com.mmedicalreport.microservicemedicalReports.service.MedicalReportService;

@SpringBootTest
public class MedicalReportServiceTest {
	
	@MockBean
	private MedicalReportService medicalReportServiceUnderTest;

	@Test
	void testAddMedicalReport() throws Exception {
		MedicalReport  medicalReportTest;
		medicalReportTest = new MedicalReport();
		medicalReportTest.setPatId(6);
		medicalReportTest.setPatient ("NamemedicalReport Test");
		List<String> Notes = new ArrayList<>();
		Notes.add("note test medical report");
		medicalReportTest.setNote(Notes);
		List<MedicalReport> AllMedicalReports = new ArrayList<>();
		 AllMedicalReports.add(medicalReportTest);
	
		when(medicalReportServiceUnderTest.addMedicalReport(medicalReportTest)).thenReturn(medicalReportTest);
		when( medicalReportServiceUnderTest.addMedicalReport(any(MedicalReport.class))).thenReturn(medicalReportTest);

		try {
			MedicalReport  resultMedicalReportCreated =  medicalReportServiceUnderTest.addMedicalReport(medicalReportTest );

			assertEquals(medicalReportTest , resultMedicalReportCreated);
		} catch (AssertionError e) {
			fail(e.getMessage());
		}
	}
}
