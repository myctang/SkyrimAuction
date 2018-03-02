package com.skyrimAuction.telegramBot;

import com.skyrimAuction.dataBaseService.entities.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SkyrimAuctionTelegramBot extends TelegramLongPollingBot {

    private void switchCommand(long chatID, String textMessage) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatID);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<String> botCommands = new ArrayList<>();
        //Going to add another commands in future time
        botCommands.add("/checkAccount");
        botCommands.add("/getInventory");
        botCommands.add("/getActualLots");
        botCommands.add("/getFinishedLots");
        botCommands.add("/getQuest");
        botCommands.add("/getTopLots");
        botCommands.add("/contactUs");
        List<KeyboardRow> commands = new ArrayList<>();
        for (String botCommand : botCommands) {
            KeyboardRow commandRow = new KeyboardRow();
            commandRow.add(botCommand);
            commands.add(commandRow);
        }
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(commands);
        replyKeyboardMarkup.setSelective(true);
        sendMessageRequest.setReplyMarkup(replyKeyboardMarkup);
        sendMessageRequest.setText(textMessage);
        try {
            execute(sendMessageRequest); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        User user = SkyrimAuctionTelegramBotService.getUserService().getUserByTGID(message.getFrom().getId());
        StringBuilder sendText = new StringBuilder();
        if (message.hasText() && user != null) {
            switch(message.getText().substring(1, 7)) {
                case "checkAc": {
                    sendText.append("Your money at ").append(new Date()).append(": ").append(user.getMoney()).append("\n");
                    break;
                }
                case "getInve": {
                    sendText.append("Your inventory at: ").append(new Date()).append("\n");
                    List<InventoryItem> itemList = SkyrimAuctionTelegramBotService.getUserService().getInventory(user);
                    for (InventoryItem item: itemList) {
                        Item currentItem = SkyrimAuctionTelegramBotService.getItemService().getItem(item.getId());
                        sendText.append("\n\nID: ").append(currentItem.getId())
                                .append("\nName: ").append(currentItem.getName())
                                .append("\nWeight: ").append(currentItem.getWeight());
                        if (currentItem.getDamage() != 0) {
                            sendText.append("\nDamage: ").append(currentItem.getDamage());
                        }
                        if (currentItem.getDefence() != 0) {
                            sendText.append("\nDefense: ").append(currentItem.getDefence());
                        }
                        sendText.append("\nQuantity: ").append(item.getQuantity());
                    }
                    break;
                }
                case "getActu": {
                    List<SellingItem> itemList = SkyrimAuctionTelegramBotService.getSellingItemService().getActualLotsByUser(user);
                    sendText.append("Your actual lots at: ").append(new Date()).append("\n");
                    for (SellingItem item: itemList) {
                        sendText.append("\n\nID: ").append(item.getId())
                                .append("\nPrice: ").append(item.getBuyNowPrice())
                                .append("\nLastBidder: ").append(item.getLastBidder().getName())
                                .append("\nItem: ").append(item.getItem().getName());
                    }
                    break;
                }
                case "getFini": {
                    List<SellingItem> itemList = SkyrimAuctionTelegramBotService.getSellingItemService().getFinishedLotsByUser(user);
                    sendText.append("Your finished lots at: ").append(new Date()).append("\n");
                    for (SellingItem item: itemList) {
                        sendText.append("\n\nID: ").append(item.getId())
                                .append("\nPrice: ").append(item.getBuyNowPrice())
                                .append("\nFinal price: ").append(item.getBuyNowPrice())
                                .append("\nLastBidder: ").append(item.getLastBidder().getName())
                                .append("\nItem: ").append(item.getItem().getName());
                    }
                    break;
                }
                case "getQues": {
                    Quest quest = user.getQuest();
                    assert quest != null;
                    sendText.append("Your actual quest: ")
                            .append("\nName: ").append(quest.getName())
                            .append("\nDescription ").append(quest.getDescription());
                    if (quest.getRewards().size() != 0) {
                        sendText.append("\n\nRewards: ");
                        for (Item item: quest.getRewards()) {
                            sendText.append("\n\n\tID: ").append(item.getId())
                                    .append("\n\tName: ").append(item.getName())
                                    .append("\n\tWeight: ").append(item.getWeight());
                            if (item.getDamage() != 0) {
                                sendText.append("\n\tDamage: ").append(item.getDamage());
                            }
                            if (item.getDefence() != 0) {
                                sendText.append("\n\tDefense: ").append(item.getDefence());
                            }
                        }
                    }
                    break;
                }
                case "contact": {
                    sendText.append("Ruslan Filichkin\n")
                            .append("ruslan.filichkin@gmail.com\n")
                            .append("Igor Popov\n")
                            .append("Belosnezhka@lyblyreact.com");
                    break;
                }
                case "getTopL": {
                    List<SellingItem> itemList = SkyrimAuctionTelegramBotService.getSellingItemService().getTheMostExpensiveLots(5);
                    sendText.append("Top lots:\n");
                    for (SellingItem item: itemList) {
                        if (item == null) {
                            continue;
                        }
                        sendText.append("\n\nID: ").append(item.getId())
                                .append("\nPrice: ").append(item.getBuyNowPrice())
                                .append("\nFinal price: ").append(item.getBuyNowPrice())
                                .append("\nLastBidder: ").append(item.getLastBidder().getName())
                                .append("\nItem: ").append(item.getItem().getName());

                    }
                }
                default: {
                    sendText.append("Can not recognize command");
                }
            }
            switchCommand(message.getChatId(), sendText.toString());
        } else {
            switchCommand(message.getChatId(),
                    "Your telegram account have not registered yet.\n" +
                    "You can connect this telegram account through Personal Room.");
        }
    }

    @Override
    public String getBotUsername() {
        return SkyrimAuctionTelegramBotConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return SkyrimAuctionTelegramBotConfig.getBotToken();
    }
}