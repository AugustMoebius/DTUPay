package service;

import data.IDataSource;

/**
 * @author Sarah
 */
public class ReportService {
    private IDataSource data;

    public ReportService(IDataSource data) {
        this.data = data;
    }

    public void generateReport(String userType, String id, String previousDate, String afterDate) {

    }
}
