<?xml version="1.0"?>
<!-- Arquivos XSLT são arquivos que nos ajudam a transformar arquivos XML em outros formatos, 
como o HTML, facilitando essa conversão. Porém não é muito utilizado atualmente no mercado, 
pois geralmente arquivos XSLT crescem muito rápido, ficando difíceis de manter. -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="venda">
        <h2>Venda</h2>
        <p>Forma de Pagamento: <xsl:value-of select="formaDePagamento" /></p>

        <!-- Para continuar a montar o template colocamos uma ref -->
        <xsl:apply-templates select="produtos" />

    </xsl:template>

    <xsl:template match="produtos">
        <xsl:apply-templates select="produto" />
    </xsl:template>

    <xsl:template match="produto">
        <h3><xsl:value-of select="nome" /></h3>
        <p>R$<xsl:value-of select="preco" /></p>
        <hr />
    </xsl:template>

</xsl:stylesheet>