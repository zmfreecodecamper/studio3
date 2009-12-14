package com.aptana.scripting.model;

import java.io.File;

import junit.framework.TestCase;

public class BundleTestBase extends TestCase
{
	private static final String APPLICATION_BUNDLES = "application-bundles";
	private static final String PROJECT_BUNDLES = "project-bundles";
	private static final String USER_BUNDLES = "user-bundles";
	
	/**
	 * loadBundleEntry
	 * 
	 * @param bundleName
	 * @param scope
	 * @return
	 */
	protected BundleEntry loadBundleEntry(String bundleName, BundleScope scope)
	{
		BundleManager manager = BundleManager.getInstance(APPLICATION_BUNDLES, USER_BUNDLES);
		String baseDirectory = null;
		
		// make sure we have a test bundle
		switch (scope)
		{
			case APPLICATION:
				baseDirectory = APPLICATION_BUNDLES;
				break;
				
			case PROJECT:
				baseDirectory = PROJECT_BUNDLES;
				break;
				
			case USER:
				baseDirectory = USER_BUNDLES;
				break;
				
			default:
				fail("Unrecognized bundle scope: " + scope);
		}
		
		File bundleFile = new File(baseDirectory + File.separator + bundleName);
		assertTrue(bundleFile.exists());
		
		// load bundle
		manager.loadBundle(bundleFile);
		BundleEntry entry = manager.getBundleEntry(bundleName);
		assertNotNull(entry);
		
		return entry;
	}

	/**
	 * loadBundle
	 * 
	 * @param bundleName
	 * @param scope
	 * @return
	 */
	protected BundleElement loadBundle(String bundleName, BundleScope scope)
	{
		BundleEntry entry = this.loadBundleEntry(bundleName, scope);
		
		BundleElement[] bundles = entry.getBundles();
		assertEquals(1, bundles.length);

		return bundles[0];
	}
}
