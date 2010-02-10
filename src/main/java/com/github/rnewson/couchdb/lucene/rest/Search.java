/*
 (C) 2010 Iron Mountain Digital
 ----------------------------
 */
package com.github.rnewson.couchdb.lucene.rest;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/{db}/{ddoc}/{view}")
public class Search {

    @GET
    @Path("search")
    @Produces({"text/plain", "application/json"})
    public String search(
            @PathParam("db") final String databaseName,
            @PathParam("ddoc") final String designDocumentName,
            @PathParam("view") final String viewName,
            @QueryParam("q") final String query,
            @DefaultValue("0") @QueryParam("skip") final int skip,
            @DefaultValue("25") @QueryParam("limit") final int limit,
            @QueryParam("sort") final List<String> sort,
            @DefaultValue("false") @QueryParam("include_docs") final boolean includeDocs) {
        return query;
    }

    @GET
    @Produces({"text/plain", "application/json"})
    public String info(
            @PathParam("db") final String databaseName,
            @PathParam("ddoc") final String designDocumentName,
            @PathParam("view") final String viewName) {
        return viewName;
    }

    @PUT
    @Path("_optimize")
    @Produces({"text/plain", "application/json"})
    public String optimize(
            @PathParam("db") final String databaseName,
            @PathParam("ddoc") final String designDocumentName,
            @PathParam("view") final String viewName) {
        return "ok";
    }

}
