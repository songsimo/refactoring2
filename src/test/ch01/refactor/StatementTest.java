package ch01.refactor;

import ch01.refactor.domain.Invoice;
import ch01.refactor.domain.Plays;
import ch01.refactor.domain.Statement;
import ch01.refactor.dto.PlayData;
import ch01.refactor.dto.StatementData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StatementTest {
    private static Invoice invoice;
    private static Plays plays;

    @BeforeAll
    static void init() throws IOException {
        ClassLoader classLoader = Statement.class.getClassLoader();

        ObjectMapper mapper = new ObjectMapper();

        Reader invoiceReader = new FileReader(classLoader.getResource("json/invoices.json").getFile());
        Reader playsReader = new FileReader(classLoader.getResource("json/plays.json").getFile());

        invoice = mapper.readValue(invoiceReader, Invoice.class);
        plays = mapper.readValue(playsReader, Plays.class);

        invoiceReader.close();
        playsReader.close();
    }

    @Test
    void statment() throws Exception {
        Statement statement = new Statement(invoice, plays);

        StatementData result = statement.statement();
        List<PlayData> playData = List.of(
                new PlayData("Hamlet", 65_000, 55),
                new PlayData("As You Like It", 58_000, 35),
                new PlayData("Othello", 50_000, 40)
                );
        StatementData expectedData = new StatementData("BigCo", playData,173_000, 47);

        assertThat(result).isEqualTo(expectedData);
    }
}