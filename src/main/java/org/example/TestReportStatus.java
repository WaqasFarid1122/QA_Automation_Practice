package org.example;

public class TestReportStatus {
    public static void main(String[] args) {
        int testStatus = 1;  // 1=Pass, 2=Fail, 3=Skipped

        System.out.println("=== Test Report Status ===");
        System.out.println("Test Status Code: " + testStatus);

        switch(testStatus) {
            case 1:
                System.out.println("✅ Test PASSED");
                break;
            case 2:
                System.out.println("❌ Test FAILED");
                break;
            case 3:
                System.out.println("⏭️ Test SKIPPED");
                break;
            default:
                System.out.println("❓ Unknown status");
        }
    }
}