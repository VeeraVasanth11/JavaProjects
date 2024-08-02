package atm;
import java.util.*;
class BankAccount{
	private String accountNumber;
	private String pin;
	private int balance;
	private ArrayList<String> transaction;

	public BankAccount(String accountNumber,String pin,int balance) {
		this.accountNumber=accountNumber;
		this.pin=pin;
		this.balance=balance;
		this.transaction=new ArrayList<String>();
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public int getBalance() {
		return balance;
	}
	public boolean validatePin(String enteredPin) {
		if(enteredPin.equals(pin)) {
			return true;
		}
		return false;
	}
	public void deposit(int amount) {
		balance+=amount;
		transaction.add("Deposit:"+amount);

	}
	public void withdrawl(int amount) {
		if(amount>balance) {
			System.out.println("Invalid Funds...");
		}
		else {
			balance-=amount;
			transaction.add("Withdrawl"+amount);
		}
	}
	public void changePin(String newPin) {
		pin=newPin;
		System.out.println("Pin Sucessfully changed..");
	}
	public void showHistory() {
		System.out.println("Transaction Historty");
		for(String history:transaction) {
			System.out.println(history);
		}
	}
}
public class ATM {
	private BankAccount currentAccount;
	private Scanner scan;
	public ATM() {
		this.scan=new Scanner(System.in);
	}
	public void start() {
		System.out.println("welcome ATM");
		authenticate();
	}
	private void authenticate() {
		System.out.println("Enter account number: ");
		String accountNumber=scan.nextLine();
		System.out.println("Enter PIN:");
		String pin=scan.nextLine();
		BankAccount account=findAccount(accountNumber);
		if(account!=null && account.validatePin(pin)) {
			currentAccount=account;
			showOptions();
		}
		else {
			System.out.println("Invalid Credentials...");
		}
	}
	public BankAccount findAccount(String accNumber) {
		if(accNumber.equals("12345678")) {
			return new BankAccount("12345678","1234",50000);
		}
		return null;
	}
	private void showOptions() {
		int ch;
		do {
		System.out.println("Enter Your Choice: \n"
				+ "1: BalanceEnquiry \n"
				+ "2: Withdrawl \n"
				+ "3: Deposit \n"
				+ "4: Pin Change \n"
				+ "5: Transaction History \n"
				+ "6: Exit \n");
		 ch=scan.nextInt();
		switch(ch) {
		case 1:
			balanceEnquiry();
			break;
		case 2:
			withdrawl();
			break;
		case 3:
			deposit();
			break;
		case 4:
			pinChange();
			break;
		case 5:
			currentAccount.showHistory();
			break;
		default:
			System.out.println("Choose correct option...");
	}
		}while(ch!=6);
		
	}
	public void balanceEnquiry() {
		 System.out.print(currentAccount.getBalance());
	}
	public void withdrawl() {
		System.out.println("enter amount:");
		int amount=scan.nextInt();
		currentAccount.withdrawl(amount);
	}
	public void deposit() {
		System.out.println("enter amount:");
		int amount=scan.nextInt();
		currentAccount.deposit(amount);
	}
	public void pinChange() {
		System.out.println("Enter new Pin");
		String newPin=scan.nextLine();
		currentAccount.changePin(newPin);
	}
	public static void main(String[] args) {
		ATM atm=new ATM();
		atm.start();
	}

}
