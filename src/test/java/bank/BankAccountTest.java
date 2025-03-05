package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    @DisplayName("Sacar dinero cuando hay saldo suficiente")
    public void withdraw_AmountMenorIgualABalance_DevuelveTrue() {
        //Arrange
        BankAccount bank = new BankAccount(1000);

        //Act
        boolean res = bank.withdraw(100);

        //Assert
        assertEquals(true, res);
    }

    @Test
    @DisplayName("Sacar dinero cuando no hay saldo suficiente")
    public void withdraw_AmountMayorABalance_DevuelveFalse() {
        //Arrange
        BankAccount bank = new BankAccount(0);

        //Act
        boolean res = bank.withdraw(100);

        //Assert
        assertEquals(false, res);
    }

    @Test
    @DisplayName("Depositar una cantidad negativa lanza excepcion")
    public void deposit_AmountNegativo_LanzaExcepcion() {
        // Arrange
        BankAccount bank = new BankAccount(0);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> {
            bank.deposit(-500);
        });
    }

    @Test
    @DisplayName("Depositar una cantidad positiva incrementa el balance")
    public void deposit_AmountPositivo_IncrementaElBalance() {
        // Arrange
        BankAccount bank = new BankAccount(0);

        // Act
        int res = bank.deposit(500);

        //Assert
        assertEquals(500, res);
    }

    @Test
    @DisplayName("Obtener el balance inicial devuelve 0")
    public void getBalance_BalanceInicialEs0_Devuelve0() {
        // Arrange
        BankAccount bank = new BankAccount(0);

        // Act
        int res = bank.getBalance();

        // Assert
        assertEquals(0, res);
    }

    @Test
    @DisplayName("Calcular el pago mensual de un préstamo")
    public void payment_CalculoCorrecto() {
        // Arrange
        BankAccount bank = new BankAccount(0);

        // Act
        double res = bank.payment(10000, 0.05, 12);

        // Assert
        assertEquals(1128.2541, res, 0.01); //ponemos un margen de error de 0.01
    }

    @Test
    @DisplayName("Devuelve el monto total del préstamo sin modificaciones con mes 0")
    public void pending_Month0_DevuelveElMontoTotal() {
        // Arrange
        BankAccount bank = new BankAccount(0);

        // Act
        double res = bank.pending(1000.0, 0.5, 12, 0);

        // Assert
        assertEquals(1000.0, res);
    }

    @Test
    @DisplayName("Obtener el pending de un préstamo después de algunos meses")
    public void pending_MonthMayorA0_DevuelveElMontoTotal() {
        // Arrange
        BankAccount bank = new BankAccount(0);
        // Act
        double res = bank.pending(1000.0, 0.5, 12, 10);
        // Assert
        assertTrue(res < 1000.0);
    }

    @Test
    @DisplayName("Obtener el pending en el último mes")
    public void pending_LastMonth_DevuelveMontoCercanoACero() {
        // Arrange
        BankAccount bank = new BankAccount(0);

        // Act
        double res = bank.pending(1000.0, 0.05, 12, 12);

        // Assert
        assertTrue(res <= 1.0);
    }

}
