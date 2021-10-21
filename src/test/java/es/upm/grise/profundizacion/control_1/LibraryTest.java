package es.upm.grise.profundizacion.control_1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
	Library c;

	@BeforeAll
	static public void beforeAll(){
		System.out.println("Starting Tests for the Library class");
	}

	@BeforeEach
	public void init(TestInfo testInfo) {
		c = new Library();
		System.out.println("Start..." + testInfo.getDisplayName());
	}
	// Tests 1 -> Se añaden libros correctamente
	// (uno o varios; comprobad la condición de libro repetido).

	@DisplayName("Test 1a -> Se añade un libro a la biblioteca")
	@Test
	public void libraryAddOneBook() throws DuplicatedBookException, NonExistingBookException, EmptyLibraryException {
		Book book1 = new Book("Moby Dick");
		c.addBook(book1);
		assertEquals(c.getBook("Moby Dick").getTitle(), book1.getTitle());
	}

	@DisplayName("Test 1b -> Se añaden varios libros a la biblioteca")
	@Test
	public void libraryAddManyBooks() throws DuplicatedBookException, NonExistingBookException, EmptyLibraryException {
		Book book1 = new Book("Dune");
		Book book2 = new Book("Oliver Twist");
		Book book3 = new Book("Moby Dick");
		c.addBook(book1);
		c.addBook(book2);
		c.addBook(book3);
		assertEquals(c.getBook("Dune").getTitle(), book1.getTitle());
		assertEquals(c.getBook("Oliver Twist").getTitle(), book2.getTitle());
		assertEquals(c.getBook("Moby Dick").getTitle(), book3.getTitle());
	}

	@DisplayName("Test 1c -> Se añade un libro repetido a la biblioteca")
	@Test
	public void libraryAddRepeatedBook() throws DuplicatedBookException {
		Book book1 = new Book("Dune");
		c.addBook(book1);
		assertThrows(DuplicatedBookException.class, () -> c.addBook(book1));
	}

	// Test 2 -> Se eliminan libros correctamente
	// (comprobar la condición de lista vacía y libro no existente).

	@DisplayName("Test 2a -> Se añaden dos libros y se elimina uno de la biblioteca")
	@Test
	public void libraryCheckIfBookIsRemoved() throws DuplicatedBookException, NonExistingBookException, EmptyLibraryException {
		Book book1 = new Book("Dune");
		Book book2 = new Book("Oliver Twist");
		c.addBook(book1);
		c.addBook(book2);
		// Comprobar que el libro está en la biblioteca
		assertEquals(c.getBook("Dune").getTitle(), book1.getTitle());
		// Eliminar el libro
		c.removeBook(book1);
		assertThrows(NonExistingBookException.class, () -> c.getBook("Dune").getTitle());
	}

	@DisplayName("Test 2b -> Se añaden dos libros y se elimina uno de la biblioteca")
	@Test
	public void libraryCheckIfAllBookAreRemoved() throws DuplicatedBookException, NonExistingBookException, EmptyLibraryException {
		Book book1 = new Book("Dune");
		Book book2 = new Book("Oliver Twist");
		c.addBook(book1);
		c.addBook(book2);
		// Comprobar que el libro está en la biblioteca
		assertEquals(c.getBook("Dune").getTitle(), book1.getTitle());
		assertEquals(c.getBook("Oliver Twist").getTitle(), book2.getTitle());
		// Eliminar los libros
		c.removeBook(book1);
		c.removeBook(book2);
		assertThrows(EmptyLibraryException.class, () -> c.getBook("Dune").getTitle());
	}

	// Test 3 -> Se localizan libros correctamente
	// (comprobar la condición de lista vacía y libro no existente).

	@DisplayName("Test 3a -> Se añade un libro y se comprueba que el libro no existe")
	@Test
	public void libraryCheckIfBookDoesNotexist() throws DuplicatedBookException {
		Book book1 = new Book("Dune");
		c.addBook(book1);
		// Comprobar que el libro no existe
		assertThrows(NonExistingBookException.class, () -> c.getBook("Oliver Twist").getTitle());
	}

	@DisplayName("Test 3b -> No se añaden libros a la biblioteca y se comprueba si la biblioteca está vacía y salta la excepcion")
	@Test
	public void libraryCheckIfLibraryIsEmpty() throws DuplicatedBookException {
		Book book1 = new Book("Dune");
		// No se añade nada a la biblioteca
		// c.addBook(book1);
		assertThrows(EmptyLibraryException.class, () -> c.getBook("Oliver Twist").getTitle());
	}








}
