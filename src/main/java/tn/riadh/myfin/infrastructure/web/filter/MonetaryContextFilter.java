package tn.riadh.myfin.infrastructure.web.filter;

import java.io.IOException;
import java.util.Currency;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tn.riadh.myfin.infrastructure.context.MonetaryContext;
import tn.riadh.myfin.infrastructure.security.StoreResolver;

public class MonetaryContextFilter extends OncePerRequestFilter {

    private final StoreResolver storeResolver;

    public MonetaryContextFilter(StoreResolver storeResolver) {
        this.storeResolver = storeResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        MonetaryContext.clear();
        try {
            // User user = resolveAuthenticatedUser();
            // Store store = storeResolver.resolveById(user.getStoreId());
            // Currency currency = Currency.getInstance(user.getCurrencyCode());
            Currency currency = Currency.getInstance("TND");
            MonetaryContext.setCurrency(currency);
            filterChain.doFilter(request, response);
        } finally {
            MonetaryContext.clear();
        }
    }

}
