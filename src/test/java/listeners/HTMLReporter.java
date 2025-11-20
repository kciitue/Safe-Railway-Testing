package listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HTMLReporter implements IReporter{
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String reportPath = "test-output/TestReport.html";

        try (PrintWriter writer = new PrintWriter(new FileWriter(reportPath))) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset='UTF-8'>");
            writer.println("<title>Test Execution Report</title>");
            writer.println("<style>");
            writer.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }");
            writer.println("h2 { color: #333; border-bottom: 2px solid #007bff; padding-bottom: 10px; }");
            writer.println("h3 { margin-top: 30px; color: #555; }");
            writer.println("table { width: 100%; border-collapse: collapse; background-color: #fff; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }");
            writer.println("th { background-color: #007bff; color: white; padding: 12px; text-align: left; border: 1px solid #ddd; white-space: nowrap; }");
            writer.println("td { padding: 10px; border: 1px solid #ddd; vertical-align: middle; }");
            writer.println(".status-pass { color: #28a745; font-weight: bold; }");
            writer.println(".status-fail { color: #dc3545; font-weight: bold; }");
            writer.println(".status-skip { color: #ffc107; font-weight: bold; }");
            writer.println(".thumb { width: 100px; height: auto; border: 1px solid #ddd; padding: 2px; cursor: pointer; transition: 0.3s; }");
            writer.println(".thumb:hover { transform: scale(1.5); }");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h2>AUTOMATED TEST REPORT</h2>");

            for (ISuite suite : suites) {
                Map<String, ISuiteResult> results = suite.getResults();
                for (ISuiteResult result : results.values()) {
                    ITestContext context = result.getTestContext();

                    writer.println("<h3>Test Suite: " + context.getName() + "</h3>");
                    writer.println("<table>");
                    writer.println("<thead><tr>");
                    writer.println("<th>Test Case Name</th>");
                    writer.println("<th>Status</th>");
                    writer.println("<th>Error Message / Exception</th>");
                    writer.println("<th>Screenshot</th>");
                    writer.println("</tr></thead>");
                    writer.println("<tbody>");

                    writeTestResults(writer, context.getFailedTests(), "FAIL");
                    writeTestResults(writer, context.getPassedTests(), "PASS");
                    writeTestResults(writer, context.getSkippedTests(), "SKIP");

                    writer.println("</tbody>");
                    writer.println("</table>");
                }
            }

            writer.println("</body></html>");
            System.out.println(">> Report generated at: " + new File(reportPath).getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeTestResults(PrintWriter writer, IResultMap resultMap, String status) {
        // 1. Lấy danh sách kết quả ra
        Set<ITestResult> resultsSet = resultMap.getAllResults();

        List<ITestResult> sortedResults = new ArrayList<>(resultsSet);

        Collections.sort(sortedResults, new Comparator<ITestResult>() {
            @Override
            public int compare(ITestResult o1, ITestResult o2) {
                return o1.getMethod().getMethodName().compareTo(o2.getMethod().getMethodName());
            }
        });

        // 4. Duyệt qua danh sách ĐÃ SẮP XẾP để in ra HTML
        for (ITestResult result : sortedResults) {
            String testName = result.getMethod().getMethodName();
            Throwable exception = result.getThrowable();
            String exceptionMessage = (exception != null) ? exception.getMessage() : "";

            String statusClass = "";
            if (status.equals("PASS")) statusClass = "status-pass";
            else if (status.equals("FAIL")) statusClass = "status-fail";
            else statusClass = "status-skip";

            String relativePath = "screenshots/" + testName + ".png";
            File imgFile = new File("test-output/" + relativePath);

            writer.println("<tr>");
            writer.println("<td>" + testName + "</td>");
            writer.println("<td class='" + statusClass + "'>" + status + "</td>");
            writer.println("<td style='font-size: 13px; color: #555;'>" + exceptionMessage + "</td>");

            writer.println("<td style='text-align: center;'>");

            if (imgFile.exists()) {
                writer.println("<a href='" + relativePath + "' target='_blank'>");
                writer.println("<img src='" + relativePath + "' class='thumb' alt='Screenshot'/>");
                writer.println("</a>");
            } else {
                writer.println("<span style='color: #999; font-size: 12px;'>No Image</span>");
            }

            writer.println("</td>");
            writer.println("</tr>");
        }
    }
}
