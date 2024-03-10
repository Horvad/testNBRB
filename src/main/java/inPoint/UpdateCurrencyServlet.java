package inPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.api.IServiceCurrency;
import service.fabric.ServiceCurrencySingleton;

import java.io.IOException;
@WebServlet(name = "UpdateCurrencyServlet", urlPatterns = "/updateCur")
public class UpdateCurrencyServlet extends HttpServlet {
    private final IServiceCurrency serviceCurrency;

    public UpdateCurrencyServlet() {
        this.serviceCurrency = ServiceCurrencySingleton.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Вызываем метод для обновления данных о валютах
        serviceCurrency.updateCurrency();

        // Возвращаем успешный статус
        resp.getWriter().println("Currency update complete.");
    }
}