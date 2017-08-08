package info.ewai.sbmt.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import info.ewai.sbmt.service.BookService;
import info.ewai.sbmt.web.form.BookForm;

@Component
public class BookValidator implements Validator {

    private static Logger logger = LoggerFactory.getLogger(BookValidator.class);

    @Autowired
    BookService bookService;

    @Override
    public boolean supports(Class<?> clazz) {
        return BookForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        logger.info("validate");

        // required check
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookName", "field.required");

        BookForm form = BookForm.class.cast(target);

        // etc check

    }
}
