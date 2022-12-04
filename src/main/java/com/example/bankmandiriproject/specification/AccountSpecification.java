package com.example.bankmandiriproject.specification;

import com.example.bankmandiriproject.dto.AccountSearchForm;
import com.example.bankmandiriproject.model.Account;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AccountSpecification implements Specification<Account> {

    private AccountSearchForm accountSearchForm;

    public AccountSpecification(AccountSearchForm accountSearchForm) {
        this.accountSearchForm = accountSearchForm;
    }

    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (!accountSearchForm.getSearchNumber().isEmpty()) {
            Predicate byNumber = criteriaBuilder.like(criteriaBuilder.lower(root.get("accountNumber")), "%" + accountSearchForm.getSearchNumber().toLowerCase(Locale.ROOT) + "%");
            predicates.add(byNumber);
        }

        if (!accountSearchForm.getSearchName().isEmpty()) {
            Predicate byName = criteriaBuilder.like(criteriaBuilder.lower(root.get("accountHolderName")), "%" + accountSearchForm.getSearchName().toLowerCase(Locale.ROOT) + "%");
            predicates.add(byName);
        }

        if (!accountSearchForm.getSearchType().isEmpty()) {
            Predicate byType = criteriaBuilder.like(criteriaBuilder.lower(root.get("accountType")), "%" + accountSearchForm.getSearchType().toLowerCase(Locale.ROOT) + "%");
            predicates.add(byType);
        }

        Predicate [] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);

        return criteriaBuilder.and(arrayPredicates);
    }
}
