package com.dev.qrscanner.main.ui.history;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dev.qrscanner.databinding.CodeItemLayoutBinding;
import com.dev.qrscanner.main.data.model.QrCodeHistoryModel;
import java.util.ArrayList;
import java.util.List;

public class QrCodesAdapter extends RecyclerView.Adapter<QrCodesAdapter.QrCodeViewHolder> {

    private final ArrayList<QrCodeHistoryModel> codeModels = new ArrayList<>();

    public void setCodes(List<QrCodeHistoryModel> codeModels) {
        if (codeModels == null || codeModels.isEmpty())
            return;
        this.codeModels.clear();
        this.codeModels.addAll(codeModels);
        notifyItemRangeChanged(0, codeModels.size());
    }

    @NonNull
    @Override
    public QrCodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CodeItemLayoutBinding binding = CodeItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new QrCodeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QrCodeViewHolder holder, int position) {
        holder.bindData(codeModels.get(position));
    }

    @Override
    public int getItemCount() {
        return codeModels.size();
    }

    public static class QrCodeViewHolder extends RecyclerView.ViewHolder {
        CodeItemLayoutBinding binding;

        public QrCodeViewHolder(@NonNull CodeItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindData(QrCodeHistoryModel qrCodeModel) {
            if (binding != null) {
                binding.tvCode.setText(qrCodeModel.getQrCode());
            }
        }

    }
}
