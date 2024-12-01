package ulpgc.es.control;


import ulpgc.es.model.Currency;
import ulpgc.es.model.ExchangeRate;
import ulpgc.es.model.Money;
import ulpgc.es.persistence.ExchangeRateLoader;
import ulpgc.es.view.CurrencyDialog;
import ulpgc.es.view.MoneyDialog;
import ulpgc.es.view.MoneyDisplay;

import java.time.LocalDate;
import java.util.List;

public class ExchangeCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader loader;
    private final MoneyDisplay moneyDisplay;
    private final List<ExchangeRate> exchangeRates;

    public ExchangeCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader loader, MoneyDisplay moneyDisplay, List<ExchangeRate> exchangeRates) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.loader = loader;
        this.moneyDisplay = moneyDisplay;
        this.exchangeRates = exchangeRates;
    }

    @Override
    public void execute() {
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();

        ExchangeRate exchangeRate = loader.load(money.currency(), currency, LocalDate.now(), exchangeRates);
        Money result = new Money(money.amount() * exchangeRate.rate(), currency);

        moneyDisplay.show(result);
    }
}
