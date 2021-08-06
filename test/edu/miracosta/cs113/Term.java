package edu.miracosta.cs113;

public class Term implements Cloneable, Comparable<Term> {
	private int coefficient, exponent;
	private final static char VARIABLE_SYMBOL = 'x';
	private final static char EXPONENT_SYMBOL = '^';
	private final static char PLUS_SYMBOL = '+';
	private final static char MINUS_SYMBOL = '-';

	// Default Constructor
	public Term() {
		coefficient = 1;
		exponent = 1;
	}

	// Full Constructor
	public Term(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}

	// Copy Constructor
	public Term(Term other) {
		this.coefficient = other.coefficient;
		this.exponent = other.exponent;
	}

	// String Constructor
	public Term(String term) {

		if (!term.isEmpty()) {

			// Looks for X Variable
			if (term.contains(Character.toString(VARIABLE_SYMBOL))) {
				// Separates Coefficient and Exponent 
				String[] splitTerm = term.split(Character.toString(VARIABLE_SYMBOL));
				this.coefficient = parseCoefficient(splitTerm[0]);

				// Has an exponent
				if (splitTerm.length == 2) {
					this.exponent = parseExponent(splitTerm[1]);
				}

				// X to the first power
				else {
					this.exponent = 1;
				}

			}
			// only has constant
			else {
				this.coefficient = parseCoefficient(term);
				this.exponent = 0;
			}
		}
		// Empty term
		else {
			this.coefficient = 0;
			this.exponent = 0;
		}
	}



	public int getCoefficient() {
		return coefficient;
	}

	public int getExponent() {
		return exponent;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}

	public void setAll(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}

	
	@Override
	public String toString() {
		String termString = "";

		if (this.coefficient != 0) {
			// Positive Term
			if (this.coefficient > 0) {
				termString += PLUS_SYMBOL;
				if (coefficient > 1) {
					termString += this.coefficient;
				}
			}

			// Negative Term
			else {
				if (coefficient == -1) {
					termString += MINUS_SYMBOL;
				} else {
					termString += this.coefficient;
				}
			}

			if (this.exponent != 0) {
				termString += VARIABLE_SYMBOL;
				if (this.exponent != 1) {
					termString += "^" + this.exponent;
				}
			}
		}

		return termString;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		Term otherTerm = (Term) other;
		return otherTerm.exponent == this.exponent && otherTerm.coefficient == this.coefficient;
	}

	public int compareTo(Term other) {
		if (other.exponent == this.exponent) {
			return 0;
		} else if (other.exponent > this.exponent) {
			return -1;
		} else {
			return 1;
		}
	}


	public Term clone() {
		Term Copy = new Term(this.coefficient, this.exponent);
		return Copy;
	}

	// Get exponent value from String
	private int parseExponent(String term) {
		return Integer.parseInt(term.substring(1));
	}

	// Get coefficient value from String
	private int parseCoefficient(String term) {
		if (term.length() == 1 && term.charAt(0) == MINUS_SYMBOL) {
			return -1;
		} else if (term.length() == 1 && term.charAt(0) == PLUS_SYMBOL) {
			return 1;
		} else {
			return Integer.parseInt(term);
		}
	}

}