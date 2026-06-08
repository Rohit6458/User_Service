package com.HealthProTeach.User.Service.DTO;

import java.util.List;

/**
 * Wrapper that encapsulates a list of DoctorSearchResponse objects
 * along with metadata like total count.
 */
public class DoctorSearchWrapper {

    private int totalCount;
    private List<DoctorSearchResponse> doctors;

    public DoctorSearchWrapper(List<DoctorSearchResponse> doctors) {
        this.doctors = doctors;
        this.totalCount = doctors != null ? doctors.size() : 0;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DoctorSearchResponse> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorSearchResponse> doctors) {
        this.doctors = doctors;
        this.totalCount = doctors != null ? doctors.size() : 0;
    }
}
