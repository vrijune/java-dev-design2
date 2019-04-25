package ictgradschool.industry.designpatternsii.examples.example01.templatemethod;

/**
 * An implementation of {@link AbstractBankAccount} to show how hook methods may be overridden to customize functionality.
 */
public class EncoreAccount extends AbstractBankAccount {

    private static final int WITHDRAWAL_FEE = 40;

    /**
     * Calculates the interest to apply to this account.
     * <p>
     * <p>This is a <strong>hook</strong> method which comprises part of the apply-interest algorithm, whose contract
     * is specified by the {@link #applyInterest()} <strong>template</strong> method.</p>
     */
    protected int calculateInterest() {
        int interest = 0;
        if (balance > 5000000)
            interest = (int) (balance * 0.065);
        else if (balance > 500000)
            interest = (int) (balance * 0.05);
        return interest;
    }

    /**
     * Sets the withdrawal fee for this account at a flat rate given by {@link #WITHDRAWAL_FEE}.
     * <p>
     * <p>This is a <strong>hook</strong> method which comprises part of the withdrawal algorithm, whose contract
     * is specified by the {@link #withdraw(int)} <strong>template</strong> method.</p>
     */
    protected int withdrawalFee() {
        return WITHDRAWAL_FEE;
    }

    /**
     * Note that this class doesn't override the {@link #isOKToWithdraw(int)} method. That's OK - not all subclasses
     * have to override every hook method (unless they're abstract).
     */

}
