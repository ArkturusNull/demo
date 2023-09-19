package com.chatbot.demo.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.FilterCriteria;
import com.google.api.services.sheets.v4.model.GridRange;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.SetDataValidationRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class HojaCalculo {
	private static final String APPLICATION_NAME = "Demo Chatbot";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "/tokens";

	// ID spreadsheet, column containing options level 0
	final String spreadsheetId = "1_ZBTPO7v18Y-03Vlv28p2OBMzdMvhlQa5c4VENOegf8";
	final String range = "chatbot2!A2:E";

	/**
	 * Global instance of the scopes required by the class. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	/**
	 * Creates an authorized Credential object.
	 *
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = HojaCalculo.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public List<List<Object>> getDataFromSheet() throws GoogleJsonResponseException {
		// Build a new authorized API client service.
		List<List<Object>> values = new ArrayList<>();

		try {
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

			Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
					.setApplicationName(APPLICATION_NAME).build();
			ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();

			values = response.getValues();

			/*
			 * for (List row : values) { // Print columns A and E, which correspond to
			 * indices 0 and 4. System.out.println(row.get(0) + " " + row.get(2));
			 * //System.out.printf("%s, %s\n", row.get(0), row.get(4)); }
			 */

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (GeneralSecurityException gse) {
			gse.printStackTrace();
		}

		return values;
	}

	public List<List<Object>> getDescripcionesPreguntas1() {
		// Build a new authorized API client service.
		List<List<Object>> values = new ArrayList<>();

		try {
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

			Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
					.setApplicationName(APPLICATION_NAME).build();
			ValueRange response = service.spreadsheets().values().get(spreadsheetId, "enunciados!A2:B").execute();

			values = response.getValues();

			/*
			 * for (List row : values) { // Print columns A and E, which correspond to
			 * indices 0 and 4. System.out.println(row.get(0) + " " + row.get(2));
			 * //System.out.printf("%s, %s\n", row.get(0), row.get(4)); }
			 */

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (GeneralSecurityException gse) {
			gse.printStackTrace();
		}

		return values;
	}

	private static final String CLIENT_EMAIL = "chatbot@prueba-lbhk.iam.gserviceaccount.com";
	private static final String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDUCPzux2YjptFTOBKtyRN760lK/rO59RxgLw3fBj+BNMch4jPfqKl3YWvTdWTjnk8pM1W8pVRBzkU+pu9KSg898VQ0kYAlTf/cixSyrDgZMvLqE2fzd57bn07hVOKljFQCuHDXdtxYx4YmoDmCcS3Zl3T0RxHMNWz3sw9gZ5jM3CwL4m/wRSq3daAnxUHEsI2Mqdwpbwv0GRVBiICCa5u2hwGDENx9StfzdKt+RxI5gxtDB/lXfRN9JzyEnBkbnRy71C08LZle+Hy89tOe10o1o9Pg9vyd6PJIVaySt1KZZGRV3J91ituDCHFTIRdrIKXwSTO4aau1RE0YXSg67a1VAgMBAAECggEAVP7xXUpjQEFbwc9FdZKYE+GidMSbCR0fbdMO7WsUw9eDR6rkfnCe1jMgRRjXA4NvWiWw5bkkiGiGS3IhlBcR+xOlXJlC1DFrMl4yQtlUd4174Kc80VUPQhyfQyaC4DfbaWfEHCsScygwN4Wc6BQ0e/gBsvZWRRTZhIWr0UpV7zBMIA9Nk36bSc1MHO8Oh3DyeKqgG2vHuAmIyYb95RxxTo6Sv6/UYGWUebR7vbsektuR8h2Gh911W7etRWoR15t6rBxOLj1yPQlm6lVVEWDR1RUJdIkunH4zRZyx37OcINvS4dr/bs9peLWYYTIC69LxjsFBfM91KnUyQoo4Ls1xVQKBgQDz3Xc16V/CFSceH9iYEydOv+rrYTxxD6SfWW8KWejR8p1f93oBdgCtyRjhH6VF2DAUOcWZ6qeEB0qe5EEMSOtSU+fafExgWXVyRp9xWnvjZp71ozhRVWzMzvY1m0AhcUDEW751225rK0myitR851v1xtblhKBt8FEKFywMLMjVXwKBgQDelgxgXTY9/1odVAIvJAfFFXz9zOLLp/VEJnKNW+4F8qYZG9orqaeHYR+fgskp3DY+b+xyzaWTbLsTMaTkXd0QSdKESSLKFZ+I1SdbzyZ3ioQPGE9EfZ1AQ4jOZqWHeEJ1lNpvUQ60uqRqHR89y+2kEmIFGQcrGXsiHAiTJ0JlywKBgARyVlkiTz+vuQ3gkMd9GetirnrqQ/rfySWqsM6kgw1I+jukvJHIQAzrLMv2EsJqRb4oq4S59aH/rAmnqdP6hGf05bZjK9VZ7D1QMloTx0yHM4DRIBbDkv9NCspBcXLqqL6r2ZY2kRXdLTPXOjao1VFu9b4ro+09Q40GjSQPsP3LAoGALh5y5AiMM55UAp2kcjYJ8hj4s/Ge0Em1tPAubgGtOMhFCBDQTJmzX4aNMdS8b5L0Hk28dTEM7JXr0RdJLhQQuM5C8yvgLHT8gDKCHDC4rK4jnKkRy4Qnb+hOUU3NYFtXkdtMwyHNZT8C0AXGslwtmgBBFQNRi+mUrMWYNT0+Ma8CgYAndCK/GEhEOQn1Vz1W9egulJxrPxhrQu8M9ZAEBspCdofCCBaDqNTflD6sbtjLK2McFp0pOVbcRBF9wevvxUkBxrxKqdanY9zdKcX6NcZF2bJu5gyXpsrAiEPE5mzgN/DggnJnmRScpgIbN8/IgItS+Ysfl2DuozPni3BDk6mL8w==";
	private static final String CREDENTIALS_FILE_PATH2 = "src/main/resources/credential.p12";

	public List<List<Object>> serviceCredentials() {
		List<List<Object>> values = new ArrayList<>();

		try {
			// Build GoogleCredential using the service account credentials
			GoogleCredential credential = new GoogleCredential.Builder()
					.setTransport(com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport())
					.setJsonFactory(com.google.api.client.json.gson.GsonFactory.getDefaultInstance())
					.setServiceAccountId(CLIENT_EMAIL)
					.setServiceAccountPrivateKeyFromP12File(new java.io.File(CREDENTIALS_FILE_PATH2))
					.setServiceAccountScopes(SCOPES).build();

			// Create a new authorized Google Sheets client
			Sheets sheetsService = new Sheets.Builder(credential.getTransport(), credential.getJsonFactory(),
					credential).setApplicationName(APPLICATION_NAME).build();

			// Example: Read data from the spreadsheet
			ValueRange response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
			values = response.getValues();

		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}
		return values;
	}

	public List<List<Object>> getDescripcionesPreguntas2() {
		List<List<Object>> values = new ArrayList<>();

		try {
			// Build GoogleCredential using the service account credentials
			GoogleCredential credential = new GoogleCredential.Builder()
					.setTransport(com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport())
					.setJsonFactory(com.google.api.client.json.gson.GsonFactory.getDefaultInstance())
					.setServiceAccountId(CLIENT_EMAIL)
					.setServiceAccountPrivateKeyFromP12File(new java.io.File(CREDENTIALS_FILE_PATH2))
					.setServiceAccountScopes(SCOPES).build();

			// Create a new authorized Google Sheets client
			Sheets sheetsService = new Sheets.Builder(credential.getTransport(), credential.getJsonFactory(),
					credential).setApplicationName(APPLICATION_NAME).build();

			// Example: Read data from the spreadsheet
			ValueRange response = sheetsService.spreadsheets().values().get(spreadsheetId, "enunciados!A2:B").execute();
			values = response.getValues();

		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}
		return values;
	}

}