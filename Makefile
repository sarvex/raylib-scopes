.spack-env: spack.yaml
	spack env create -d .
	spack -e . install

sanity:
	spack env activate . && scopes -e -m {{main_module_name}}.sanity
.PHONY: sanity

clean:
	rm -rf .spack-env
.PHONY: clean
