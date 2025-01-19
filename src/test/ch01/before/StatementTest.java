package ch01.before;

import ch01.CommonConst;
import ch01.dto.Invoice;
import ch01.dto.Plays;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

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
        Statement statement = new Statement();

        String result = statement.statement(invoice, plays);

        assertThat(result).isEqualTo(CommonConst.EXPECTED_INVOICE_MESSAGE);
    }
}