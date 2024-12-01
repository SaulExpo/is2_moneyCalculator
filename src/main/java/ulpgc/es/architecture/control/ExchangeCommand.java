package ulpgc.es.architecture.control;


import ulpgc.es.architecture.model.Currency;
import ulpgc.es.architecture.model.ExchangeRate;
import ulpgc.es.architecture.model.Money;
import ulpgc.es.architecture.persistence.ExchangeRateLoader;
import ulpgc.es.architecture.view.CurrencyDialog;
import ulpgc.es.architecture.view.MoneyDialog;
import ulpgc.es.architecture.view.MoneyDisplay;

import java.time.LocalDate;

public class ExchangeCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader loader;
    private final MoneyDisplay moneyDisplay;

    public ExchangeCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader loader, MoneyDisplay moneyDisplay) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.loader = loader;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute() {
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();

        ExchangeRate exchangeRate = loader.load(money.currency(), currency, LocalDate.now());
        Money result = new Money(money.amount() * exchangeRate.rate(), currency);

        moneyDisplay.show(result);
    }
}
