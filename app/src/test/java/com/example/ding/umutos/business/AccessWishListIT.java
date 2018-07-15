package com.example.ding.umutos.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.ding.umutos.objects.Wish;
import com.example.ding.umutos.utils.TestUtils;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
public class AccessWishListIT {

    private AccessWishlists accessWishlists;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.accessWishlists = new AccessWishlists();
    }

    @After
    public void tearDown() {
        this.tempDB.delete();
        accessWishlists = null;
    }

    @Test
    public void testGetWishList()
    {
        System.out.println("\nStarting test null testGetWishList.\n");
        assertNotNull(accessWishlists.getWishList());
        System.out.println("\nEnd test null testGetWishList.\n");
    }

    @Test
    public void testGetUserWishList()
    {
        System.out.println("\nStarting test testGetUserWishList.\n");
        assertNotNull(accessWishlists.getUserWishLists("Xiao Peng"));
        assertNull(accessWishlists.getUserWishLists("Tianhua Xu"));
        assertTrue(accessWishlists.getUserWishLists("Xiao Peng").size() == 2);
        System.out.println("\nEnd test testGetUserWishList.\n");
    }

    @Test
    public void testInsertWishList()
    {
        System.out.println("\nStarting test testInsertWishList.\n");
        Wish newWish = new Wish(5, "Yu Gu", "Henri Pirenne", "Economic and Social History of Medieval Europe");
        assertTrue(accessWishlists.insertWishList(newWish));
        assertTrue(accessWishlists.getUserWishLists("Yu Gu").size() == 1);
        System.out.println("\nEnd test testInsertWishList.\n");
    }

    @Test
    public void testDeleteWishList()
    {
        System.out.println("\nStarting test testDeleteWishList.\n");
        accessWishlists.deleteWishList(1, "Xiao Peng");
        assertTrue(accessWishlists.getUserWishLists("Xiao Peng").size() == 2);
        System.out.println("\nStarting test testDeleteWishList.\n");
    }
}
