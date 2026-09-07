package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class KompaAPITesting {
    private static final String BASE_URL = "https://api.kompa.example.com";
    private static String authToken = "";
    private static List<Map<String, Object>> testResults = new ArrayList<>();
    private static int passCount = 0;
    private static int failCount = 0;

    public static void main(String[] args) {
        System.out.println("🚀 KOMPA MUSIC API TESTING STARTED");
        System.out.println("=" .repeat(60));

        // Phase 1: Authentication Tests
        testUserRegistration();
        testUserLogin();
        testTokenRefresh();

        // Phase 2: User Management Tests
        testGetUserProfile();
        testUpdateUserProfile();
        testChangePassword();

        // Phase 3: Music/Content Tests
        testGetAllSongs();
        testSearchSongs();
        testGetArtists();
        testGetAlbums();

        // Phase 4: Playlist Tests
        testCreatePlaylist();
        testGetPlaylists();
        testAddSongToPlaylist();
        testDeletePlaylist();

        // Phase 5: Favorites Tests
        testAddFavoriteSong();
        testGetFavorites();
        testRemoveFavorite();

        // Generate Report
        generateHTMLReport();

        System.out.println("=" .repeat(60));
        System.out.println("✅ API TESTING COMPLETED");
        System.out.println("Total Tests: " + (passCount + failCount));
        System.out.println("✓ Passed: " + passCount);
        System.out.println("✗ Failed: " + failCount);
        double passRate = (passCount * 100.0) / (passCount + failCount);
        System.out.println("📊 Pass Rate: " + String.format("%.2f", passRate) + "%");
    }

    // ========== PHASE 1: AUTHENTICATION TESTS ==========
    private static void testUserRegistration() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"email\": \"testuser" + System.currentTimeMillis() + "@test.com\",\n" +
                            "  \"password\": \"SecurePass123!\",\n" +
                            "  \"name\": \"Test User\"\n" +
                            "}")
                    .when()
                    .post("/auth/register")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 201) {
                addResult("Test 1", "User Registration", "PASS", "User registered successfully");
                passCount++;
            } else {
                addResult("Test 1", "User Registration", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 1", "User Registration", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testUserLogin() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .body("{\n" +
                            "  \"email\": \"user@test.com\",\n" +
                            "  \"password\": \"SecurePass123!\"\n" +
                            "}")
                    .when()
                    .post("/auth/login")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                authToken = response.jsonPath().getString("token");
                addResult("Test 2", "User Login", "PASS", "Login successful, token obtained");
                passCount++;
            } else {
                addResult("Test 2", "User Login", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 2", "User Login", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testTokenRefresh() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .body("{\"refreshToken\": \"token123\"}")
                    .when()
                    .post("/auth/refresh")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                addResult("Test 3", "Token Refresh", "PASS", "Token refreshed successfully");
                passCount++;
            } else {
                addResult("Test 3", "Token Refresh", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 3", "Token Refresh", "ERROR", e.getMessage());
            failCount++;
        }
    }

    // ========== PHASE 2: USER MANAGEMENT TESTS ==========
    private static void testGetUserProfile() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + authToken)
                    .when()
                    .get("/users/profile")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                addResult("Test 4", "Get User Profile", "PASS", "Profile retrieved successfully");
                passCount++;
            } else {
                addResult("Test 4", "Get User Profile", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 4", "Get User Profile", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testUpdateUserProfile() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .body("{\n" +
                            "  \"name\": \"Updated Name\",\n" +
                            "  \"bio\": \"Music Lover\"\n" +
                            "}")
                    .when()
                    .put("/users/profile")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                addResult("Test 5", "Update User Profile", "PASS", "Profile updated successfully");
                passCount++;
            } else {
                addResult("Test 5", "Update User Profile", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 5", "Update User Profile", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testChangePassword() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .body("{\n" +
                            "  \"oldPassword\": \"OldPass123!\",\n" +
                            "  \"newPassword\": \"NewPass456!\"\n" +
                            "}")
                    .when()
                    .post("/users/change-password")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                addResult("Test 6", "Change Password", "PASS", "Password changed successfully");
                passCount++;
            } else {
                addResult("Test 6", "Change Password", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 6", "Change Password", "ERROR", e.getMessage());
            failCount++;
        }
    }

    // ========== PHASE 3: MUSIC/CONTENT TESTS ==========
    private static void testGetAllSongs() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + authToken)
                    .when()
                    .get("/music/songs")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                addResult("Test 7", "Get All Songs", "PASS", "Songs retrieved successfully");
                passCount++;
            } else {
                addResult("Test 7", "Get All Songs", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 7", "Get All Songs", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testSearchSongs() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + authToken)
                    .queryParam("q", "Kompa Music")
                    .when()
                    .get("/music/search")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                addResult("Test 8", "Search Songs", "PASS", "Search successful");
                passCount++;
            } else {
                addResult("Test 8", "Search Songs", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 8", "Search Songs", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testGetArtists() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + authToken)
                    .when()
                    .get("/music/artists")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                addResult("Test 9", "Get Artists", "PASS", "Artists retrieved successfully");
                passCount++;
            } else {
                addResult("Test 9", "Get Artists", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 9", "Get Artists", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testGetAlbums() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + authToken)
                    .when()
                    .get("/music/albums")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                addResult("Test 10", "Get Albums", "PASS", "Albums retrieved successfully");
                passCount++;
            } else {
                addResult("Test 10", "Get Albums", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 10", "Get Albums", "ERROR", e.getMessage());
            failCount++;
        }
    }

    // ========== PHASE 4: PLAYLIST TESTS ==========
    private static void testCreatePlaylist() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .body("{\n" +
                            "  \"name\": \"My Kompa Playlist\",\n" +
                            "  \"description\": \"Best Kompa songs\"\n" +
                            "}")
                    .when()
                    .post("/playlists")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 201) {
                addResult("Test 11", "Create Playlist", "PASS", "Playlist created successfully");
                passCount++;
            } else {
                addResult("Test 11", "Create Playlist", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 11", "Create Playlist", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testGetPlaylists() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + authToken)
                    .when()
                    .get("/playlists")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                addResult("Test 12", "Get Playlists", "PASS", "Playlists retrieved successfully");
                passCount++;
            } else {
                addResult("Test 12", "Get Playlists", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 12", "Get Playlists", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testAddSongToPlaylist() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .body("{\"songId\": \"song123\"}")
                    .when()
                    .post("/playlists/1/songs")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 201 || response.getStatusCode() == 200) {
                addResult("Test 13", "Add Song to Playlist", "PASS", "Song added successfully");
                passCount++;
            } else {
                addResult("Test 13", "Add Song to Playlist", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 13", "Add Song to Playlist", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testDeletePlaylist() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + authToken)
                    .when()
                    .delete("/playlists/1")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 204 || response.getStatusCode() == 200) {
                addResult("Test 14", "Delete Playlist", "PASS", "Playlist deleted successfully");
                passCount++;
            } else {
                addResult("Test 14", "Delete Playlist", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 14", "Delete Playlist", "ERROR", e.getMessage());
            failCount++;
        }
    }

    // ========== PHASE 5: FAVORITES TESTS ==========
    private static void testAddFavoriteSong() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .body("{\"songId\": \"song123\"}")
                    .when()
                    .post("/favorites")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 201 || response.getStatusCode() == 200) {
                addResult("Test 15", "Add Favorite Song", "PASS", "Song added to favorites");
                passCount++;
            } else {
                addResult("Test 15", "Add Favorite Song", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 15", "Add Favorite Song", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testGetFavorites() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + authToken)
                    .when()
                    .get("/favorites")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 200) {
                addResult("Test 16", "Get Favorites", "PASS", "Favorites retrieved successfully");
                passCount++;
            } else {
                addResult("Test 16", "Get Favorites", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 16", "Get Favorites", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void testRemoveFavorite() {
        try {
            Response response = RestAssured
                    .given()
                    .baseUri(BASE_URL)
                    .header("Authorization", "Bearer " + authToken)
                    .when()
                    .delete("/favorites/song123")
                    .then()
                    .extract()
                    .response();

            if (response.getStatusCode() == 204 || response.getStatusCode() == 200) {
                addResult("Test 17", "Remove Favorite", "PASS", "Favorite removed successfully");
                passCount++;
            } else {
                addResult("Test 17", "Remove Favorite", "FAIL", "Status: " + response.getStatusCode());
                failCount++;
            }
        } catch (Exception e) {
            addResult("Test 17", "Remove Favorite", "ERROR", e.getMessage());
            failCount++;
        }
    }

    private static void addResult(String testNum, String testName, String status, String details) {
        Map<String, Object> result = new HashMap<>();
        result.put("testNum", testNum);
        result.put("testName", testName);
        result.put("status", status);
        result.put("details", details);
        result.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        testResults.add(result);

        String emoji = status.equals("PASS") ? "✅" : status.equals("FAIL") ? "❌" : "⚠️";
        System.out.println(emoji + " " + testNum + ": " + testName + " - " + status);
    }

    private static void generateHTMLReport() {
        try {
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>\n");
            html.append("<html>\n");
            html.append("<head>\n");
            html.append("<meta charset='UTF-8'>\n");
            html.append("<title>Kompa API Test Report</title>\n");
            html.append("<style>\n");
            html.append("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f5f5f5; }\n");
            html.append(".header { background-color: #FF6B35; color: white; padding: 20px; border-radius: 5px; }\n");
            html.append(".summary { display: flex; gap: 20px; margin: 20px 0; }\n");
            html.append(".card { background: white; padding: 15px; border-radius: 5px; flex: 1; text-align: center; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }\n");
            html.append(".card h3 { margin: 0; color: #666; }\n");
            html.append(".card .number { font-size: 32px; font-weight: bold; color: #FF6B35; }\n");
            html.append("table { width: 100%; border-collapse: collapse; background: white; margin-top: 20px; }\n");
            html.append("th { background-color: #333; color: white; padding: 12px; text-align: left; }\n");
            html.append("td { padding: 10px; border-bottom: 1px solid #ddd; }\n");
            html.append("tr:hover { background-color: #f9f9f9; }\n");
            html.append(".pass { color: green; font-weight: bold; }\n");
            html.append(".fail { color: red; font-weight: bold; }\n");
            html.append(".error { color: orange; font-weight: bold; }\n");
            html.append("</style>\n");
            html.append("</head>\n");
            html.append("<body>\n");
            html.append("<div class='header'>\n");
            html.append("<h1>🎵 Kompa Music API Testing Report</h1>\n");
            html.append("<p>Comprehensive API Endpoint Testing</p>\n");
            html.append("</div>\n");

            html.append("<div class='summary'>\n");
            html.append("<div class='card'>\n");
            html.append("<h3>Total Tests</h3>\n");
            html.append("<div class='number'>").append(passCount + failCount).append("</div>\n");
            html.append("</div>\n");
            html.append("<div class='card'>\n");
            html.append("<h3>Passed</h3>\n");
            html.append("<div class='number' style='color: green;'>").append(passCount).append("</div>\n");
            html.append("</div>\n");
            html.append("<div class='card'>\n");
            html.append("<h3>Failed</h3>\n");
            html.append("<div class='number' style='color: red;'>").append(failCount).append("</div>\n");
            html.append("</div>\n");
            double passRate = (passCount * 100.0) / (passCount + failCount);
            html.append("<div class='card'>\n");
            html.append("<h3>Pass Rate</h3>\n");
            html.append("<div class='number'>").append(String.format("%.1f", passRate)).append("%</div>\n");
            html.append("</div>\n");
            html.append("</div>\n");

            html.append("<h2>Test Results</h2>\n");
            html.append("<table>\n");
            html.append("<tr><th>Test #</th><th>Test Name</th><th>Status</th><th>Details</th><th>Timestamp</th></tr>\n");

            for (Map<String, Object> result : testResults) {
                String status = (String) result.get("status");
                String statusClass = status.equals("PASS") ? "pass" : status.equals("FAIL") ? "fail" : "error";
                html.append("<tr>\n");
                html.append("<td>").append(result.get("testNum")).append("</td>\n");
                html.append("<td>").append(result.get("testName")).append("</td>\n");
                html.append("<td class='").append(statusClass).append("'>").append(status).append("</td>\n");
                html.append("<td>").append(result.get("details")).append("</td>\n");
                html.append("<td>").append(result.get("timestamp")).append("</td>\n");
                html.append("</tr>\n");
            }

            html.append("</table>\n");
            html.append("</body>\n");
            html.append("</html>\n");

            String filename = "API_Test_Report.html";
            java.nio.file.Files.write(java.nio.file.Paths.get(filename), html.toString().getBytes());
            System.out.println("\n📄 Report saved: " + filename);
        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}