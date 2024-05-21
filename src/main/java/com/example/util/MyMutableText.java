package com.example.util;


import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.Formatting;
import net.minecraft.util.Language;
import org.jetbrains.annotations.Nullable;

/**
 * The text implementation, with mutation operations.
 */
public class MyMutableText
        implements Text {
    private TextContent content;
    private final List<Text> siblings;
    private Style style;
    private OrderedText ordered = OrderedText.EMPTY;
    @Nullable
    private Language language;

    MyMutableText(TextContent content, List<Text> siblings, Style style) {
        this.content = content;
        this.siblings = siblings;
        this.style = style;
    }

    /**
     * Creates a piece of mutable text with the given content, with no sibling
     * and style.
     */
    public static MyMutableText of(TextContent content) {
        return new MyMutableText(content, Lists.newArrayList(), Style.EMPTY);
    }

    @Override
    public TextContent getContent() {
        return this.content;
    }

    @Override
    public List<Text> getSiblings() {
        return this.siblings;
    }

    /**
     * Sets the style of this text.
     */
    public MyMutableText setStyle(Style style) {
        this.style = style;
        return this;
    }

    @Override
    public Style getStyle() {
        return this.style;
    }

    /**
     * Appends a literal text with content {@code text} to this text's siblings.
     *
     * @param text the literal text content
     */
    public MyMutableText append(String text) {
        return this.append(Text.literal(text));
    }

    /**
     * Appends a text to this text's siblings.
     *
     * @param text the sibling
     */
    public MyMutableText append(Text text) {
        this.siblings.add(text);
        return this;
    }

    /**
     * Updates the style of this text.
     *
     * @see Text#getStyle()
     * @see #setStyle(Style)
     *
     * @param styleUpdater the style updater
     */
    public MyMutableText styled(UnaryOperator<Style> styleUpdater) {
        this.setStyle((Style)styleUpdater.apply(this.getStyle()));
        return this;
    }

    /**
     * Fills the absent parts of this text's style with definitions from {@code
     * styleOverride}.
     *
     * @see Style#withParent(Style)
     *
     * @param styleOverride the style that provides definitions for absent definitions in this text's style
     */
    public MyMutableText fillStyle(Style styleOverride) {
        this.setStyle(styleOverride.withParent(this.getStyle()));
        return this;
    }

    /**
     * Adds some formattings to this text's style.
     *
     * @param formattings an array of formattings
     */
    public MyMutableText formatted(Formatting ... formattings) {
        this.setStyle(this.getStyle().withFormatting(formattings));
        return this;
    }

    /**
     * Add a formatting to this text's style.
     *
     * @param formatting a formatting
     */
    public MyMutableText formatted(Formatting formatting) {
        this.setStyle(this.getStyle().withFormatting(formatting));
        return this;
    }

    /**
     * {@return the text with the RGB color {@code color}}
     */
    public MyMutableText withColor(int color) {
        this.setStyle(this.getStyle().withColor(color));
        return this;
    }

    @Override
    public OrderedText asOrderedText() {
        Language language = Language.getInstance();
        if (this.language != language) {
            this.ordered = language.reorder(this);
            this.language = language;
        }
        return this.ordered;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof MyMutableText) {
            MyMutableText myMutableText = (MyMutableText)o;
            return this.content.equals(myMutableText.content) && this.style.equals(myMutableText.style) && this.siblings.equals(myMutableText.siblings);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.content, this.style, this.siblings);
    }

    public String toString() {
        boolean bl2;
        StringBuilder stringBuilder = new StringBuilder(this.content.toString());
        boolean bl = !this.style.isEmpty();
        boolean bl3 = bl2 = !this.siblings.isEmpty();
        if (bl || bl2) {
            stringBuilder.append('[');
            if (bl) {
                stringBuilder.append("style=");
                stringBuilder.append(this.style);
            }
            if (bl && bl2) {
                stringBuilder.append(", ");
            }
            if (bl2) {
                stringBuilder.append("siblings=");
                stringBuilder.append(this.siblings);
            }
            stringBuilder.append(']');
        }
        return stringBuilder.toString();
    }
    public void setTextContent(TextContent contentRef){
        this.content = contentRef;
    }
}


