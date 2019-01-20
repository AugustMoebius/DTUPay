package service;

import data.IDataSource;

public class ReportService {
    private IDataSource data;

    public ReportService(IDataSource data) {
        this.data = data;
    }

    public void generateReport(String id, String previousDate, String afterDate) {

    }
}
