package pl.coderslab.springhibernatemod6.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.springhibernatemod6.dao.AuthorDao;
import pl.coderslab.springhibernatemod6.entity.Author;

@Component
public class AuthorConverter implements Converter<String, Author> {
private final AuthorDao authorDao;

public AuthorConverter(AuthorDao authorDao) {
    this.authorDao = authorDao;
}
@Override
    public Author convert(String source) {
    return authorDao.findAuthorID(Integer.parseInt(source));
}
}
