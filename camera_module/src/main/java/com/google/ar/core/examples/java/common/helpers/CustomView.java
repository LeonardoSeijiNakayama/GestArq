package com.google.ar.core.examples.java.common.helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CustomView extends View {
    private Paint paint;
    private List<Line> lines = new ArrayList<>(); // Lista para armazenar as linhas desenhadas
    private List<TextLabel> textLabels = new ArrayList<>(); // Lista para armazenar os textos

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setTextSize(40);
        paint.setAntiAlias(true);
    }

    // Método para adicionar texto a ser exibido


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Desenhar todas as linhas armazenadas

    }

    // Método para adicionar uma nova linha


    // Classe interna para armazenar os dados de uma linha
    private static class Line {
        float startX, startY, endX, endY;

        Line(float startX, float startY, float endX, float endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    // Classe interna para armazenar os textos
    private static class TextLabel {
        String text;
        float x, y;

        TextLabel(String text, float x, float y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }
    }
}
