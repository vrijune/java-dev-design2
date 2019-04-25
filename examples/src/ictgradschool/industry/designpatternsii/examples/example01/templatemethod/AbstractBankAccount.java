package ictgradschool.industry.designpatternsii.examples.example01.templatemethod;

/**
 * A really simple bank account class demonstrating the template method pattern.
 */
public abstract class AbstractBankAccount {
    protected int balance;

    /**
     * Withdraws the given amount of money from the account if it is OK to do so.
     *
     * <p>This is a <strong>template</strong> method - it defines the general withdrawal algorithm, which can be
     * customized by subclasses who override the {@link #isOKToWithdraw(int)} and / or {@link #withdrawalFee()}
     * <strong>hook</strong> methods.</p>
     *
     * <p>Note that this method is <strong>final</strong>, meaning it cannot be overridden. This ensures the general
     * contract of the method remains the same, and only the parts we want to be customizable may be changed.</p>
     */
    public final boolean withdraw(int amount) {
        boolean success = false;
        if (amount >= 1 && isOKToWithdraw(amount)) {
            balance -= amount;
            balance -= withdrawalFee();
            success = true;
        }
        return success;
    }

    /**
     * Applies interest to this account.
     *
     * <p>This is a <strong>template</strong> method - it defines the general apply-interest algorithm, which can be
     * customized by subclasses who override the {@link #calculateInterest()} <strong>hook</strong> method.</p>
     *
     * <p>Note that this method is <strong>final</strong>, meaning it cannot be overridden. This ensures the general
     * contract of the method remains the same, and only the parts we want to be customizable may be changed.</p>
     */
    public final void applyInterest() {
        int interest = calculateInterest();
        balance += interest;
    }

    /**
     * When overridden in subclasses, calculates the amount of interest to apply to this account.
     *
     * <p>This s a <strong>hook</strong> method - it defines part of the apply-interest algorithm which may
     * (and in this case, must) be customized by subclasses.</p>
     *
     * @return the amount of interest to apply.
     */
    protected abstract int calculateInterest();

    /**
     * Determines whether it is OK to withdraw money from this account. By default, it is always OK to withdraw.
     *
     * <p>This s a <strong>hook</strong> method - it defines part of the withdrawal algorithm which may be customized
     * by subclasses. Note that methods may be hook methods, even if they are not abstract.</p>
     *
     * @return true if it's ok to withdraw, false otherwise
     */
    protected boolean isOKToWithdraw(int amount) {
        return true;
    }

    /**
     * Calculates the fee for making a withdrawal from this account. By default, there is no fee.
     *
     * <p>This s a <strong>hook</strong> method - it defines part of the withdrawal algorithm which may be customized
     * by subclasses. Note that methods may be hook methods, even if they are not abstract.</p>
     *
     * @return the fee for making a withdrawal from this account.
     */
    protected int withdrawalFee() {
        return 0;
    }
}
