package com.flippingutilities.wealthtracking.model;


import com.flippingutilities.model.AccountData;
import com.flippingutilities.model.FlippingItem;
import com.flippingutilities.model.OfferEvent;
import com.flippingutilities.ui.uiutilities.TimeFormatters;
import com.google.gson.Gson;
import com.google.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


// AccountData ->  List<FlippingItem> trades -<List>-> HistoryManager


public class WealthHistoryManager
{

	private AccountData accountData;
	@Inject	private Gson gson;

	public WealthHistoryManager(AccountData accountData)
	{
		this.accountData = accountData;
	}

	public AccountData loadFromFile(File f) throws IOException
	{
		String accountDataJson = new String(Files.readAllBytes(f.toPath()));
		return gson.fromJson(accountDataJson, AccountData.class);
	}


	public static void exportToCsv(File file, List<FlippingItem> trades, String startOfIntervalName) throws IOException {
		FileWriter out = new FileWriter(file);
		CSVPrinter csvWriter = new CSVPrinter(out,
			CSVFormat.DEFAULT.
				withHeader("name", "date", "quantity", "price", "state").
				withCommentMarker('#').
				withHeaderComments("Displaying trades for selected time interval: " + startOfIntervalName));

		for (FlippingItem item : trades) {
			for (OfferEvent offer : item.getHistory().getCompressedOfferEvents()) {
				csvWriter.printRecord(
					item.getItemName(),
					TimeFormatters.formatInstantToDate(offer.getTime()),
					offer.getCurrentQuantityInTrade(),
					offer.getPrice(),
					offer.getState()
				);
			}
			csvWriter.printComment(String.format("Total profit: %d", FlippingItem.getProfit(item.getHistory().getCompressedOfferEvents())));
			csvWriter.println();
		}
		csvWriter.close();
	}
}

