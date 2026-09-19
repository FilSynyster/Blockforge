package agenda.app.repositories;

import agenda.database.annotations.Query;
import agenda.database.core.DataRepository;
import agenda.app.models.Contact;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public interface ContactRepository extends DataRepository<Contact, Long> {

    // =========================================================
    // RETORNO SIMPLES
    // =========================================================

    @Query("SELECT * FROM contacts WHERE name = ?")
    Contact findByName(String name);


    @Query("SELECT * FROM contacts WHERE id = ?")
    Contact findByIdNative(Long id);


    // =========================================================
    // LIST
    // =========================================================

    @Query("SELECT * FROM contacts")
    List<Contact> findAllNative();


    @Query("SELECT * FROM contacts WHERE age > 10")
    List<Contact> findByAge();


    @Query("SELECT * FROM contacts WHERE age > ?")
    List<Contact> findByAgeGreaterThan(int age);


    // =========================================================
    // SET
    // =========================================================

    @Query("SELECT * FROM contacts")
    Set<Contact> findAllAsSet();


    @Query("SELECT * FROM contacts WHERE age > 10")
    Set<Contact> findAdultsAsSet();


    // =========================================================
    // COLLECTION
    // =========================================================

    @Query("SELECT * FROM contacts")
    Collection<Contact> findAllAsCollection();


    @Query("SELECT * FROM contacts WHERE age > 10")
    Collection<Contact> findByAgeAsCollection();


    // =========================================================
    // OPTIONAL
    // =========================================================

    @Query("SELECT * FROM contacts WHERE name = ?")
    Optional<Contact> findOptionalByName(String name);


    @Query("SELECT * FROM contacts WHERE id = ?")
    Optional<Contact> findOptionalById(Long id);


    // =========================================================
    // STREAM
    // =========================================================

    @Query("SELECT * FROM contacts")
    Stream<Contact> findAllAsStream();


    @Query("SELECT * FROM contacts WHERE age > 10")
    Stream<Contact> findByAgeAsStream();
}