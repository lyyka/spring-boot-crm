package com.example.lr20190024.clients.services.filters;

import com.example.lr20190024.clients.entities.Client;
import com.example.lr20190024.common.filters.FilterBuilder;
import com.example.lr20190024.common.filters.FilterRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Getter
@Setter
@AllArgsConstructor
public class ClientIndexFilterRequest extends FilterRequest<Client> {
    private String search;
    @DateTimeFormat
    private String createdAtFrom;
    @DateTimeFormat
    private String createdAtTo;

    @Override
    public FilterBuilder<Client> getFilterBuilder() {
        FilterBuilder<Client> filterBuilder = new FilterBuilder<>();

        if (this.search != null) {
            filterBuilder
                    .and()
                    .like(this.search, "firstName", "lastName")
                    .like("email", this.search)
                    .like("phoneNumber", this.search);
        }

        if (this.createdAtFrom != null) {
            try {
                filterBuilder.and().gte("createdAt", (new SimpleDateFormat("y-M-d")).parse(this.createdAtFrom));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        if (this.createdAtTo != null) {
            try {
                filterBuilder.and().lte("createdAt", (new SimpleDateFormat("y-M-d")).parse(this.createdAtTo));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return filterBuilder;
    }
}
