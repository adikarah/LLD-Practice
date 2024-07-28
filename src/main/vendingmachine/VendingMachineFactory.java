package main.vendingmachine;

import main.vendingmachine.service.VendingMachineImpl;

public class VendingMachineFactory {

    public static VendingMachineImpl createVendingMachine() {
        return new VendingMachineImpl();
    }

    public static void main(String[] args) {
        VendingMachineImpl vendingMachine = VendingMachineFactory.createVendingMachine();
        System.out.println(vendingMachine.getTotalSales());
        vendingMachine.printStats();

    }
}
