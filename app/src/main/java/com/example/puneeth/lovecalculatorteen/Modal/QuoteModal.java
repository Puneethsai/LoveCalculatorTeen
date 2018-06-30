package com.example.puneeth.lovecalculatorteen.Modal;

import java.io.Serializable;

public class QuoteModal implements Serializable {

    public String quote, author, category ;

    public QuoteModal(String quote, String author, String category) {
        this.quote = quote;
        this.author = author;
        this.category = category;
    }

    public QuoteModal() {
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}