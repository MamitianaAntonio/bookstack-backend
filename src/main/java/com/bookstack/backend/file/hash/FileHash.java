package com.bookstack.backend.file.hash;

import com.bookstack.backend.PojaGenerated;

@PojaGenerated
public record FileHash(FileHashAlgorithm algorithm, String value) {}
