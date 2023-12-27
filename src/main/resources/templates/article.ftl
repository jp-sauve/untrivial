<#-- @ftlvariable name="article" type="<com.example.models.Article>" -->
 <#import "_layout.ftl" as layout />
 <@layout.header>
 <div>
    <h3>Article</h3>
    <div id="article">
        <div id="title">
        <h3>${article.title}</h3>
        <span>[<a href="/article/${article.id}/edit">Edit</a>]</span>
        </div>
        <div id="body">
        <p>${article.body}</p>
        </div>
    </div>
 </div>
 </@layout.header>