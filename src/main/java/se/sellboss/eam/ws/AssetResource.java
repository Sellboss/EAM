package se.sellboss.eam.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.domain.AssetSearchCriteria;
import se.sellboss.eam.service.AssetService;

/**
 * Providing REST interface with JSON data to AssetService.
 * 
 * 
 * @author Martin
 * 
 */
@Controller
@Path("/asset")
public class AssetResource {

	@Autowired
	private AssetService assetService;

	/**
	 * Search providing id or part of name of asset. Returns an array of assets
	 * in JSON-format.
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@GET
	@Path("/query")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Asset> getXML(@QueryParam("id") String id,
			@QueryParam("name") String name) {

		AssetSearchCriteria searchCriteria = new AssetSearchCriteria();
		if (null != name && !name.isEmpty()) {
			searchCriteria.setAssetName(name);
		}
		if (null != id && !id.isEmpty()) {
			searchCriteria.setAssetId(id);
		}
		List<Asset> assetList = assetService.searchByCriteria(searchCriteria);

		return assetList;
	}

	/**
	 * Get asset using id. Returns a single asset as JSON-data.
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public Asset getXML(@QueryParam("id") String id) {

		AssetSearchCriteria searchCriteria = new AssetSearchCriteria();
		if (null != id && !id.isEmpty()) {
			searchCriteria.setAssetId(id);
		}
		List<Asset> assetList = assetService.searchByCriteria(searchCriteria);

		Asset asset = new Asset();
		asset = assetList.get(0);

		return asset;
	}

}

// curl -i -H "Accept: application/json" http://localhost:8080/EAM/rest/todo