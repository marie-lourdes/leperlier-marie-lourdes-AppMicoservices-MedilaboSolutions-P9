package com.massessment.microserviceassessment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.massessment.microserviceassessment.beans.MedicalReportBean;
import com.massessment.microserviceassessment.beans.PatientBean;
import com.massessment.microserviceassessment.proxy.IMicroserviceMedicalReportsProxy;
import com.massessment.microserviceassessment.proxy.IMicroservicePatientsProxy;
import com.massessment.microserviceassessment.utils.ConstantRiskDiabete;
import com.massessment.microserviceassessment.utils.Constants;

@Component
public class EvaluatorDiabeteImpl implements IEvaluatorRiskDiabete {
	private ICounter counterTermsMedicalReportNotes;
	private IFilter filterInfoPatient;
	private IMicroservicePatientsProxy microservicePatientsProxy;
	private IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy;
	private Integer numberOfSymptoms;
	private String riskEvaluated;

	public EvaluatorDiabeteImpl(IMicroserviceMedicalReportsProxy microserviceMedicalReportsProxy,
			IMicroservicePatientsProxy microservicePatientsProxy, ICounter counterTermsMedicalReportNotes) {
		this.microservicePatientsProxy = microservicePatientsProxy;
		this.microserviceMedicalReportsProxy = microserviceMedicalReportsProxy;
		this.filterInfoPatient = new FilterInfoPatientImpl(microservicePatientsProxy);
		this.counterTermsMedicalReportNotes = new CounterTermsMedicalReportNotesImpl();

	}

	public Integer countSymptomFromMedicalReportNotes(Integer id) {
		List<MedicalReportBean> medicalReportsBeans = this.getMedicalReportBean(id);
		List<List<String>> notes = medicalReportsBeans.stream().map(medicalReport -> medicalReport.getNote())
				.collect(Collectors.toList());

		for (List<String> note : notes) {
			numberOfSymptoms = counterTermsMedicalReportNotes.countSymptomFromMedicalReportNotes(note);
		}
		return numberOfSymptoms;
	}

	public String evaluateRiskDiabeteOfPatient(Integer id) throws NullPointerException {
		PatientBean patientBean = this.getPatientBean(id);
		System.out.println("Evaluating risk dibete for patient:  " + id + " " + patientBean.getNom());

		riskEvaluated = null;
		if (riskEvaluated == null) {
			riskEvaluated = evaluateAsRiskNone(patientBean.getId());
		}

		if (riskEvaluated == null) {
			riskEvaluated = evaluateAsRiskBorderLine(patientBean.getId());
		}

		if (riskEvaluated == null) {
			riskEvaluated = evaluateAsRiskDanger(patientBean.getId());
		}

		if (riskEvaluated == null) {
			riskEvaluated = evaluateAsRiskEarlyOnSet(patientBean.getId());
		}

		if (riskEvaluated == null) {
			System.out.println("Failed to evaluate risk diabete for patient " + id);
		}
		return riskEvaluated;
	}

	@Override
	public String evaluateAsRiskNone(Integer id) {
		numberOfSymptoms = countSymptomFromMedicalReportNotes(id);
		return numberOfSymptoms == 0 ? ConstantRiskDiabete.RISK_NONE : null;
	}

	@Override
	public String evaluateAsRiskBorderLine(Integer id) {
		numberOfSymptoms = countSymptomFromMedicalReportNotes(id);
		boolean isMoreThan30Years = filterInfoPatient.filterAgeLimitPatient(id, 30);
		if (numberOfSymptoms >= 2 && numberOfSymptoms <= 5 && isMoreThan30Years) {
			return ConstantRiskDiabete.RISK_BORDERLINE;
		}

		return null;
	}

	@Override
	public String evaluateAsRiskDanger(Integer id) {
		numberOfSymptoms = countSymptomFromMedicalReportNotes(id);
		boolean isMoreThan30Years = filterInfoPatient.filterAgeLimitPatient(id, 30);
		String isMasculinOrFeminin = filterInfoPatient.filterSexPatient(id);

		if (!isMoreThan30Years) {
			if (numberOfSymptoms == 3 && isMasculinOrFeminin.equals(Constants.MASCULIN)) {
				return ConstantRiskDiabete.RISK_IN_DANGER;
			} else if (numberOfSymptoms == 4 && isMasculinOrFeminin.equals(Constants.FEMININ)) {
				return ConstantRiskDiabete.RISK_IN_DANGER;
			}
		}

		if (isMoreThan30Years) {
			if (numberOfSymptoms == 6 || numberOfSymptoms == 7) {
				return ConstantRiskDiabete.RISK_IN_DANGER;
			}
		}
		return null;
	}

	@Override
	public String evaluateAsRiskEarlyOnSet(Integer id) {
		numberOfSymptoms = countSymptomFromMedicalReportNotes(id);
		boolean isMoreThan30Years = filterInfoPatient.filterAgeLimitPatient(id, 30);
		String isMasculinOrFeminin = filterInfoPatient.filterSexPatient(id);

		if (!isMoreThan30Years) {
			if (numberOfSymptoms >= 5 && isMasculinOrFeminin.equals(Constants.MASCULIN)) {
				return ConstantRiskDiabete.RISK_EARLY_ON_SET;
			} else if (numberOfSymptoms >= 7 && isMasculinOrFeminin.equals(Constants.FEMININ)) {
				return ConstantRiskDiabete.RISK_EARLY_ON_SET;
			}
		}

		if (isMoreThan30Years) {
			if (numberOfSymptoms > 8) {
				return ConstantRiskDiabete.RISK_EARLY_ON_SET;
			}
		}
		return null;
	}

	public List<MedicalReportBean> getMedicalReportBean(Integer patientId) {
		return microserviceMedicalReportsProxy.getMedicalReportsByPatId(patientId);
	}

	private PatientBean getPatientBean(Integer id) {
		return microservicePatientsProxy.getPatientById(id);
	}
	/*public List<List<String>>  getMedicalReportNotes(Integer patientId) {
		List<List<String>> listMedicalReportOfPatient=this.getMedicalReportBean(patientId).stream()
				.map(medicalReport-> medicalReport.getNote())
				.collect(Collectors.toList());
		//listMedicalReportOfPatient.stream().
		System.out.println("-----------------------listMedicalReportOfPatientNotes--------------------: "+listMedicalReportOfPatient);
		return listMedicalReportOfPatient;
	}*/
}
