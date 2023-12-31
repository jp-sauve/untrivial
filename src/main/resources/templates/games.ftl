<#-- @ftlvariable name="games" type="kotlin.collections.List<com.example.models.Game>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <#list games?reverse as game>
        <div>
            <h3>
                <a href="/game/${game.id}">${game.name}</a>
            </h3>
            <p>
                ${game.variant}
            </p>
        </div>
    </#list>
    <hr>
    <p>
        <a href="/game/new">Create game</a>
    </p>
</@layout.header>