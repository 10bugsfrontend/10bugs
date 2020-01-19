package com.rezervare;

public enum StatusType {
	ACTIVE, // o rezervare odata plasata trece in statusul active
	CANCELLED, // o rezervare anulata
	COMPLETED, // o rezervare la care s-a prezentat
	OVERDUE, // o rezervare la care clientul nu s-a prezentat dar nici nu a anuntat
	PENDING // o rezervare a carei data a trecut, dar firma nu a zis daca e onorata sau nu
			// inca

}