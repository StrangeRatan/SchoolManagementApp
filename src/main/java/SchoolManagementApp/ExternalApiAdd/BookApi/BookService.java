package SchoolManagementApp.ExternalApiAdd.BookApi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<BookDto> searchBooks(String title) {
        String url = "https://openlibrary.org/search.json?title=" + title;

        Map response = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> docs = (List<Map<String, Object>>) response.get("docs");
        List<BookDto> books = new ArrayList<>();

        for (Map<String, Object> doc : docs) {
            BookDto book = new BookDto();
            book.setTitle((String) doc.get("title"));
            book.setAuthorName((List<String>) doc.get("author_name"));
            book.setFirstPublishYear((Integer) doc.get("first_publish_year"));
            book.setIsbn((List<String>) doc.get("isbn"));
            books.add(book);
        }

        return books;
    }
}
