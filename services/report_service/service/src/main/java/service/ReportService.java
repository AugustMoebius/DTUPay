package service;

import data.IDataSource;

/**
 * @author Sarah (s153659)
 */
public class ReportService {
    private IDataSource data;

    public ReportService(IDataSource data) {
        this.data = data;
    }

    public void generateReport(String userType, String id, String previousDate, String afterDate) {

    }
}
