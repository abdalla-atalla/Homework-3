package edu.miracosta.cs113;

import java.util.LinkedList;

public class Polynomial {
	private LinkedList<Term> listOfTerms; // Terms List

	// Constructors
	public Polynomial() {
		this.listOfTerms = new LinkedList<Term>();
	}

	// Terms List #2 aka the copy
	public Polynomial(Polynomial other) {
		listOfTerms = new LinkedList<Term>();
		for (int i = 0; i < other.getNumTerms(); i++) {
			this.addTerm(new Term(other.getTerm(i)));
		}

	}

	// Getters
	public int getNumTerms() {
		return listOfTerms.size();
	}

	public Term getTerm(int i) {
		return listOfTerms.get(i);
	}

	public LinkedList<Term> getPolyList() {
		return listOfTerms;
	}

	// adds a term
	public void addTerm(Term term) {

		// case 1: there is nothing, just add to the beginning
		if (listOfTerms.isEmpty()) {
			listOfTerms.offerFirst(term);
		}
		// case 2: its not empty, now add
		else {
			for (int i = 0; i < this.listOfTerms.size(); i++) {
				// check if they are similar, if yes they are then these are like terms and
				// continue
				if (listOfTerms.get(i).getExponent() == term.getExponent()) {
					// if these coefficients add up to 0 remove them bc they will cancel
					if (listOfTerms.get(i).getCoefficient() + term.getCoefficient() == 0) {
						listOfTerms.remove(i);
					}

					// if it makes it to here then add the coefficients & set the new sum of
					// coefficients
					else {
						int sumCoefficients = listOfTerms.get(i).getCoefficient() + term.getCoefficient();
						this.listOfTerms.get(i).setCoefficient(sumCoefficients);
					}
					// finished here now break to go to next case
					break;
				}

				// case 3: the List only has one term, compare the exponents and if the new term
				// is greater put it before the term that was already present
				else if (listOfTerms.size() == 1) {
					if (listOfTerms.get(0).getExponent() < term.getExponent()) {
						listOfTerms.offerFirst(term);
						break;
					} // if its is less than then put it in the end/ after the term that is already
						// present
					else {
						listOfTerms.offerLast(term);
						break;
					}
				}

				//case 4: the exponents aren't equal so just add the term
				else if (i == (this.listOfTerms.size() - 1)) {
					listOfTerms.add(term);
					break; 
				} else if (listOfTerms.get(i + 1).getExponent() < term.getExponent()) {
					listOfTerms.add(i + 1, term);
					break; 

				}
			}
		}

	}

	public void add(Polynomial other) {
		// Add each term from Other to the polynomial
		for (int i = 0; i < other.listOfTerms.size(); i++) {
			this.addTerm(other.getTerm(i));
		}
	}

	public void clear() {
		listOfTerms.clear();
	}

	public void remove(int index) {
		listOfTerms.remove(index);
	}

	@Override
	public String toString() {
		String list = "";
		if (this.listOfTerms == null) {
			return "null";
		}
		if (this.listOfTerms.isEmpty()) {
			return "0";
		}

		// if the first term is positive
		if (this.listOfTerms.get(0).getCoefficient() > 0) {
			list += this.listOfTerms.get(0).toString().substring(1);
		}

		// then the first term is negative
		else {
			list += this.listOfTerms.get(0).toString();
		}

		// then puts the rest of the polynomial to string
		for (int i = 1; i < listOfTerms.size(); i++) {
			list += this.listOfTerms.get(i);
		}
		return list;
	}

}