package fr.plaglefleau.cashless.models.input

data class EditTextInput(val isEnabled: Boolean, val isVisible: Boolean) : Input(isEnabled, isVisible) {}