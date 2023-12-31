<#-- @ftlvariable name="game" type="<com.example.models.Game>" -->
 <#import "_layout.ftl" as layout />
 <@layout.header>
 <div>
    <h3>Game</h3>
    <div id="article">
        <div id="title">
        <h3>${game.name}</h3>
        <h5>${game.id}</h5>
        <span>[<a href="/game/${game.id}/edit">Edit</a>]</span>
        </div>
        <div id="body">
        <p>${game.variant}</p>
        </div>
    </div>
 </div>
 </@layout.header>