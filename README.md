**Overview**
Safe Railway Testing is an automated testing project designed to ensure the safety, stability, and correctness of the Safe Railway web application.
It leverages Java, TestNG, and Selenium WebDriver to automate functional testing and generate detailed reports.

**Project Structure**
src/ – Contains source code and test cases.
test-output/ – Stores reports generated after test execution.
pom.xml – Maven configuration file for dependency management.
testng.xml – TestNG configuration file defining suites and test cases.
.idea/ – IDE configuration (IntelliJ).
.gitignore – Specifies files/folders excluded from version control.
**Technologies Used**
Java (20.9%) – Main language for test case implementation.
HTML (71.8%) – Web interface under test.
CSS (4.6%) & JavaScript (2.7%) – UI styling and client-side logic.
TestNG – Testing framework for organizing and running test cases.
Selenium WebDriver – Browser automation for UI testing.
Maven – Build and dependency management tool.
**How to Run**
Clone the repository:
bash
git clone https://github.com/kciitue/Safe-Railway-Testing.git
cd Safe-Railway-Testing
Install dependencies:
bash
mvn clean install
Run tests with TestNG:
bash
mvn test
Or run the testng.xml file directly from your IDE.
**View reports:**
After execution, reports are available in the test-output/ directory.
**Test Results**
Each test case produces a PASSED/FAILED status with execution time.
Reports can be viewed in HTML format for a clear, visual summary.
