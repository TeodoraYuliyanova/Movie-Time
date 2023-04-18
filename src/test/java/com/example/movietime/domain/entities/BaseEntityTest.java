package com.example.movietime.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BaseEntityTest {

    @InjectMocks
    private BaseEntityImpl baseEntity;

    @Test
    public void testGetId() {
        Long expectedId = 1L;
        baseEntity.setId(expectedId);

        Long actualId = baseEntity.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testSetId() {
        Long expectedId = 2L;

        baseEntity.setId(expectedId);

        assertEquals(expectedId, baseEntity.getId());
    }

    private static class BaseEntityImpl extends BaseEntity {
    }

}