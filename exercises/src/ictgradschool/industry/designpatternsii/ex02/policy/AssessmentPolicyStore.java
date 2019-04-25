package ictgradschool.industry.designpatternsii.ex02.policy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Class to represent a collection of AssessmentPolicies.
 * 
 */
public class AssessmentPolicyStore {

	private List<AssessmentPolicy> _policies;

	/**
	 * Creates an AssesmentPolicyStore object, populated with a set of policies.
	 */
	public AssessmentPolicyStore() {
		_policies = new ArrayList<AssessmentPolicy>();
		_policies.add(new FiftyZeroFifty());
		_policies.add(new OneHundredZeroZero());
		_policies.add(new SeventyTenTwenty());
		_policies.add(new ZeroZeroOneHundred());
	}

	/**
	 * Returns the default assessment policy object.
	 */
	public AssessmentPolicy getDefaultPolicy() {
		return _policies.get(0);
	}

	/**
	 * Returns the set of AssessmentPolicy objects known to the
	 * AssessmentPolicyStore.
	 */
	public Collection<AssessmentPolicy> getAllPolicies() {
		return new ArrayList<AssessmentPolicy>(_policies);
	}

	/**
	 * Returns a particular AssessmentPolicy whose name is equal to the
	 * policyName argument.
	 * 
	 * @param policyName
	 *            the name of the AssessmentPolicy sought.
	 * @return the AssessmentPolicy object whose name equals that of the
	 *         policyName argument. In cases where there is no AssessmentPolicy
	 *         object known by the given name, this method returns null.
	 */
	public AssessmentPolicy getPolicy(String policyName) {
		AssessmentPolicy result = null;
		boolean found = false;
		Iterator<AssessmentPolicy> i = _policies.iterator();

		while ((!found) && i.hasNext()) {
			AssessmentPolicy next = i.next();
			if (next.toString().equals(policyName)) {
				found = true;
				result = next;
			}
		}
		return result;
	}
}
